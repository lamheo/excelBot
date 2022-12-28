package koidira.product.excelFormularBot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.File;
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
        browserUtil.doLogin(this.driver, "nt91600@email.vccs.edu", "Koidira@269");
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

