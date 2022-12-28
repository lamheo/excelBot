package koidira.product.excelFormularBot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import koidira.product.excelFormularBot.shared.RequestData;
import koidira.product.excelFormularBot.shared.ResponseData;
import koidira.product.excelFormularBot.shared.ResponseDataStatus;

import koidira.product.excelFormularBot.dto.request.*;
import koidira.product.excelFormularBot.dto.response.ResponseDto;

import koidira.product.excelFormularBot.service.RequestService;

import koidira.product.excelFormularBot.utility.BrowserUtil;


@RestController
@RequestMapping("/request")
public class RequestController {
    private final BrowserUtil browserUtil;
    private final RequestService requestService;
    private WebDriver driver;

    @Autowired
    public RequestController(BrowserUtil browserUtil, RequestService requestService) {
        this.browserUtil = browserUtil;
        this.requestService = requestService;
    }

    @GetMapping("/init")
    public String init() throws Exception {
        this.driver = browserUtil.init();
        this.driver.get("https://excelformulabot.com/login?status=login");
        browserUtil.doLogin(this.driver, "tuanit@outlook.com", "Koidira@269");
        File SrcFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File DestFile=new File("/home/pham/mySpace/excelFormularBot/java/excelFormularBot/testing/Temp5.png");

              //Copy file at destination
        FileUtils.copyFile(SrcFile, DestFile);
        return browserUtil.getCurrentUrl(this.driver);
    }

    @PostMapping()
    public ResponseData<ResponseDto> request(
      @RequestBody RequestData<RequestCreationDto> requestData) throws Exception {
    if (requestData.isRequestEmpty()) {
      return ResponseData.<ResponseDto>builder()
          .status(ResponseDataStatus.ERROR)
          .message("REQUEST_DATA_IS_EMPTY")
          .build();
    }
    ResponseDto responseDto = requestService.getResponse(this.driver, requestData.getData());
    if (responseDto == null) {
      return ResponseData.<ResponseDto>builder()
          .status(ResponseDataStatus.ERROR)
          .message("ERROR")
          .build();
    }
    return ResponseData.<ResponseDto>builder()
        .status(ResponseDataStatus.SUCCESS)
        .message("SUCCESS")
        .data(responseDto)
        .build();
  }


}

