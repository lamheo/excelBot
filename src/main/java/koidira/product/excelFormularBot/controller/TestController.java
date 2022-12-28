package koidira.product.excelFormularBot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import koidira.product.excelFormularBot.utility.BrowserUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {
    private final BrowserUtil browserUtil;
    private WebDriver driver;

    @Autowired
    public TestController(BrowserUtil browserUtil) {
        this.browserUtil = browserUtil;
    }

    @GetMapping("/init")
    public String init() throws Exception {
        this.driver = browserUtil.init();
        this.driver.get("https://excelformulabot.com/login?status=login");
        browserUtil.doLogin(this.driver, "nt91600@email.vccs.edu", "Koidira@269");
        return browserUtil.getCurrentUrl(this.driver);
    }

    @PostMapping("/request")
    public String request() throws Exception {
        return browserUtil.ask(this.driver);
    }

}

