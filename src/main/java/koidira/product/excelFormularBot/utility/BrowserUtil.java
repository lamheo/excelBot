package koidira.product.excelFormularBot.utility;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Component;
import java.io.File;
import java.time.Duration;
// import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import javax.security.auth.login.LoginException;

@Component
public class BrowserUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrowserUtil.class);
    
    @Autowired
    public BrowserUtil() {

    }

    public WebDriver init()  {
      ChromeOptions options = new ChromeOptions();
      options.addArguments(
        "--no-sandbox",
        "--disable-notifications",
        "--disable-infobars",
        "--disable-extensions",
        "--disable-gpu",
        "--disable-dev-shm-usage",
        "--window-size=1920,1080",
        "--headless"
      );

      WebDriver driver = new ChromeDriver(options);
      
      return driver;
    }

    public String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public void doLogin(WebDriver driver, String username, String password) {
      try {
        WebElement emailInput = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='email']")));
        emailInput.sendKeys(username);
        WebElement passwordInput = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='password']")));
        passwordInput.sendKeys(password);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Login']"))).click();
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']"))).click();
      } catch (Exception e) {
        System.out.print(e);
      }
    }

    public String ask(WebDriver driver) throws Exception {
      String question = "SUm a1 and b1";
      driver.findElement(By.xpath("//button[text()='Excel']")).click();
      driver.findElement(By.xpath("//button[text()='Generate']")).click();
      List<WebElement> textAreaFields = driver.findElement(By.id("generator")).findElements(
          By.tagName("textarea")
      );
      WebElement inputField = textAreaFields.get(0);
      inputField.click();
      inputField.clear();
      System.out.print("Send: "+question);
      inputField.sendKeys(question);
      WebElement submitButton = driver.findElement(By.id("generator")).findElement(
          By.id("submit_click")
      );
      submitButton.click();

      Thread.sleep(2000);

      WebElement responseField = textAreaFields.get(1);

      System.out.print("Respone: ");
      String response = responseField.getAttribute("value");
      System.out.print(response);
      return response;
    }

    public String requestDriver(WebDriver driver, boolean isExcel, boolean isGenerate, String request) throws Exception {
      if (isExcel) {
        driver.findElement(By.xpath("//button[text()='Excel']")).click();
      } else {
        driver.findElement(By.xpath("//button[text()='Google Sheets']")).click();

      }
      if (isGenerate) {
        driver.findElement(By.xpath("//button[text()='Generate']")).click();
      } else {
        driver.findElement(By.xpath("//button[text()='Explain']")).click();
      }
      List<WebElement> textAreaFields = driver.findElement(By.id("generator")).findElements(
          By.tagName("textarea")
      );
      WebElement inputField = textAreaFields.get(0);
      inputField.click();
      inputField.clear();
      System.out.print("Send: "+request);
      inputField.sendKeys(request);
      WebElement submitButton = driver.findElement(By.id("generator")).findElement(
          By.id("submit_click")
      );
      submitButton.click();

      Thread.sleep(2000);

      WebElement responseField = textAreaFields.get(1);

      System.out.print("Respone: ");
      String response = responseField.getAttribute("value");
      System.out.print(response);
      return response;
    }

  
}