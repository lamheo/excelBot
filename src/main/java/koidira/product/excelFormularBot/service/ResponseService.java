package koidira.product.excelFormularBot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.apache.commons.lang3.BooleanUtils;
import org.modelmapper.ModelMapper;

import koidira.product.excelFormularBot.entity.Response;

import koidira.product.excelFormularBot.dto.response.ResponseDto;
import koidira.product.excelFormularBot.dto.response.ResponseCreationDto;
import koidira.product.excelFormularBot.dto.response.ResponseUpdateDto;


import koidira.product.excelFormularBot.repository.ResponseRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Objects;

import java.time.OffsetDateTime;


@Service
public class ResponseService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ResponseService.class);

  private final ResponseRepo responseRepo;
  private final ModelMapper modelMapper;

  public ResponseService(ResponseRepo responseRepo, ModelMapper modelMapper) {
    this.responseRepo = responseRepo;
    this.modelMapper = modelMapper;
  }

  public ResponseDto createResponse(ResponseCreationDto responseCreationDto) {
    Response response = modelMapper.map(responseCreationDto, Response.class);
    responseRepo.save(response);
    return modelMapper.map(response, ResponseDto.class);
  }

  public List<Response> getResponseList() {
    List<Response> responseList = responseRepo.findAll();
    return responseList.stream()
        .filter(response -> BooleanUtils.isNotTrue(response.getDeleted()))
        .collect(Collectors.toList());
  }

  public List<ResponseDto> getResponseDtoList() {
    List<ResponseDto> responseDtoList = new ArrayList<>();
    List<Response> responses = getResponseList();
    for (Response response : responses) {
      ResponseDto responseDto = modelMapper.map(response, ResponseDto.class);
      responseDtoList.add(responseDto);
    }
    return responseDtoList;
  }

  public Response getResponseById(Long id) {
    if (Objects.isNull(id)) {
      return null;
    }
    Optional<Response> _response = responseRepo.findById(id);
    if (_response.isEmpty()) {
      return null;
    }
    Response response = _response.get();
    if (BooleanUtils.isTrue(response.getDeleted())) {
      return null;
    }
    return response;
  }

  public ResponseDto getResponseDtoById(Long id) {
    Response response = getResponseById(id);
    if (response == null) {
      return null;
    }
    return modelMapper.map(response, ResponseDto.class);
  }

  public ResponseDto updateResponse(
      Long responseId, ResponseUpdateDto responseUpdateDto) {
    if (!Objects.equals(responseUpdateDto.getId(), responseId)) {
      return null;
    }
    Response response = getResponseById(responseId);
    if (response == null) {
      return null;
    }
    response = modelMapper.map(responseUpdateDto, Response.class);
    response = responseRepo.save(response);
    return modelMapper.map(response, ResponseDto.class);
  }

  public Long deleteResponse(Long responseId) {
    Response response = getResponseById(responseId);
    if (response == null) {
      return null;
    }
    response.setDeleted(true);
    response = responseRepo.save(response);
    return response.getId();
  }
}
