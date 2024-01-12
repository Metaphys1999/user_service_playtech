package com.user.service.service.contract;

import com.user.service.dto.CreateUserDto;
import com.user.service.dto.GetUserDto;
import com.user.service.dto.UpdateUserDto;
import com.user.service.entity.User;

import java.util.List;

public interface UserService {

    CreateUserDto createUser(CreateUserDto createUserDto);

    UpdateUserDto updateUser(Integer userId, UpdateUserDto updateUserDto);

    GetUserDto getUser(Integer userId);

    List<GetUserDto> getUsers();

    String deleteUser(Integer userId);
}
