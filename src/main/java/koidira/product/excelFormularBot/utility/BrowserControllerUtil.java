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
import org.apache.commons.io.FileUtils;
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
import javax.annotation.PreDestroy;

@Component
public class BrowserControllerUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrowserControllerUtil.class);
    private final WebDriver driver;
    
    @Autowired
    public BrowserControllerUtil(WebDriver driver) {
      this.driver = driver;
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
        "--headless",
        "--disable-popup-blocking"
      );

      WebDriver driver = new ChromeDriver(options);
      
      return driver;
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public void doLogin(String username, String password) {
      try {
        driver.get("https://excelformulabot.com/login?status=login");
        // driver.get("https://gitlab.com/");

        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File("/home/pham/mySpace/excelFormularBot/java/excelFormularBot/testing/evi.png");
        System.out.print("teakescreen");
        FileUtils.copyFile(SrcFile, DestFile);
        
        WebElement emailInput = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='email']")));
        emailInput.sendKeys(username);
        WebElement passwordInput = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[type='password']")));
        passwordInput.sendKeys(password);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Login']"))).click();
        SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        DestFile = new File("/home/pham/mySpace/excelFormularBot/java/excelFormularBot/testing/evi2.png");
        System.out.print("teakescreen");
        FileUtils.copyFile(SrcFile, DestFile);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Yes']"))).click();
        SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        DestFile = new File("/home/pham/mySpace/excelFormularBot/java/excelFormularBot/testing/evi3.png");
        System.out.print("teakescreen");
        FileUtils.copyFile(SrcFile, DestFile);
      } catch (Exception e) {
        System.out.print(e);
      }
    }

    public String ask() throws Exception {
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

    public String requestDriver(boolean isExcel, boolean isGenerate, String request) throws Exception {
      driver.findElement(By.xpath("//div[text()='Formulas']")).click();
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

    @PreDestroy
    public void cleanUp() {
        // Perform cleanup tasks here
        System.out.print("clean up here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    public void quit() {
      driver.quit();
    }
}