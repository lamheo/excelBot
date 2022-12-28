package koidira.product.excelFormularBot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.apache.commons.lang3.BooleanUtils;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Objects;
import java.time.OffsetDateTime;

import org.openqa.selenium.WebDriver;


import koidira.product.excelFormularBot.entity.Request;
import koidira.product.excelFormularBot.dto.request.RequestDto;
import koidira.product.excelFormularBot.dto.request.RequestCreationDto;
import koidira.product.excelFormularBot.repository.RequestRepo;
import koidira.product.excelFormularBot.utility.BrowserUtil;

import koidira.product.excelFormularBot.dto.response.ResponseDto;
import koidira.product.excelFormularBot.dto.response.ResponseCreationDto;


import koidira.product.excelFormularBot.utility.BrowserUtil;


@Service
public class RequestService {
  private static final Logger LOGGER = LoggerFactory.getLogger(RequestService.class);

  private final RequestRepo requestRepo;
  private final ModelMapper modelMapper;
  private final ResponseService responseService;
  private final BrowserUtil browserUtil;

  public RequestService(RequestRepo requestRepo, ModelMapper modelMapper, ResponseService responseService, BrowserUtil browserUtil) {
    this.requestRepo = requestRepo;
    this.modelMapper = modelMapper;
    this.responseService = responseService;
    this.browserUtil = browserUtil;
  }

  public RequestDto createRequest(RequestCreationDto requestCreationDto) {
    Request request = modelMapper.map(requestCreationDto, Request.class);
    System.out.print(request);
    requestRepo.save(request);
    return modelMapper.map(request, RequestDto.class);
  }


  public List<Request> getRequestList() {
    List<Request> requestList = requestRepo.findAll();
    return requestList.stream()
        .filter(request -> BooleanUtils.isNotTrue(request.getDeleted()))
        .collect(Collectors.toList());
  }

  public List<RequestDto> getRequestDtoList() {
    List<RequestDto> requestDtoList = new ArrayList<>();
    List<Request> requests = getRequestList();
    for (Request request : requests) {
      RequestDto requestDto = modelMapper.map(request, RequestDto.class);
      requestDtoList.add(requestDto);
    }
    return requestDtoList;
  }

  public Request getRequestById(Long id) {
    if (Objects.isNull(id)) {
      return null;
    }
    Optional<Request> _request = requestRepo.findById(id);
    if (_request.isEmpty()) {
      return null;
    }
    Request request = _request.get();
    if (BooleanUtils.isTrue(request.getDeleted())) {
      return null;
    }
    return request;
  }

  public RequestDto getRequestDtoById(Long id) {
    Request request = getRequestById(id);
    if (request == null) {
      return null;
    }
    return modelMapper.map(request, RequestDto.class);
  }

  public Long deleteRequest(Long requestId) {
    Request request = getRequestById(requestId);
    if (request == null) {
      return null;
    }
    request.setDeleted(true);
    request = requestRepo.save(request);
    return request.getId();
  }

  public ResponseDto getResponse(WebDriver webDriver, RequestCreationDto requestCreationDto) throws Exception {
    //save request
    //get response
    //save response
    //return
    RequestDto requestDto = createRequest(requestCreationDto);

    boolean isExcel = requestCreationDto.isExcel();
    boolean isGenerate = requestCreationDto.isGenerate();
    String request = requestCreationDto.getRequest();

    String response = browserUtil.requestDriver(webDriver, isExcel, isGenerate, request);

    ResponseCreationDto responseCreationDto = ResponseCreationDto.builder()
      .requestId(requestDto.getId())
      .result("SUCCESS")
      .response(response)
      .executionTime(Long.valueOf(10))
      .vote(Long.valueOf(0))
      .build();

    return responseService.createResponse(responseCreationDto);
  }
}
