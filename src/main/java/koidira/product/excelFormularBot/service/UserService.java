package koidira.product.excelFormularBot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.apache.commons.lang3.BooleanUtils;
import org.modelmapper.ModelMapper;

import koidira.product.excelFormularBot.entity.User;

import koidira.product.excelFormularBot.dto.user.UserDto;
import koidira.product.excelFormularBot.dto.user.UserCreationDto;
import koidira.product.excelFormularBot.dto.user.UserUpdateDto;


import koidira.product.excelFormularBot.repository.UserRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Objects;

import java.time.OffsetDateTime;


@Service
public class UserService {
  private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

  private final UserRepo userRepo;
  private final ModelMapper modelMapper;

  public UserService(UserRepo userRepo, ModelMapper modelMapper) {
    this.userRepo = userRepo;
    this.modelMapper = modelMapper;
  }

  public List<User> getUserList() {
    List<User> userList = userRepo.findAll();
    return userList.stream()
        .filter(user -> BooleanUtils.isNotTrue(user.getDeleted()))
        .collect(Collectors.toList());
  }

  public List<UserDto> getUserDtoList() {
    List<UserDto> userDtoList = new ArrayList<>();
    List<User> users = getUserList();
    for (User user : users) {
      UserDto userDto = modelMapper.map(user, UserDto.class);
      userDtoList.add(userDto);
    }
    return userDtoList;
  }

  public User getUserById(Long id) {
    if (Objects.isNull(id)) {
      return null;
    }
    Optional<User> _user = userRepo.findById(id);
    if (_user.isEmpty()) {
      return null;
    }
    User user = _user.get();
    if (BooleanUtils.isTrue(user.getDeleted())) {
      return null;
    }
    return user;
  }

  public UserDto getUserDtoById(Long id) {
    User user = getUserById(id);
    if (user == null) {
      return null;
    }
    return modelMapper.map(user, UserDto.class);
  }

  public UserDto updateUser(
      Long userId, UserUpdateDto userUpdateDto) {
    if (!Objects.equals(userUpdateDto.getId(), userId)) {
      return null;
    }
    User user = getUserById(userId);
    if (user == null) {
      return null;
    }
    user = modelMapper.map(userUpdateDto, User.class);
    user = userRepo.save(user);
    return modelMapper.map(user, UserDto.class);
  }

  public Long deleteUser(Long userId) {
    User user = getUserById(userId);
    if (user == null) {
      return null;
    }
    user.setDeleted(true);
    user = userRepo.save(user);
    return user.getId();
  }
}
