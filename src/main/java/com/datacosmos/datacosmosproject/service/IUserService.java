package com.datacosmos.datacosmosproject.service;

import com.datacosmos.datacosmosproject.Dto.UserDto;
import com.datacosmos.datacosmosproject.entities.User;

import java.util.List;


public interface IUserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();

}
