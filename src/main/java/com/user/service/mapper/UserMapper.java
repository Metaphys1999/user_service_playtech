package com.user.service.mapper;


import com.user.service.dto.CreateUserDto;
import com.user.service.dto.GetUserDto;
import com.user.service.dto.UpdateUserDto;
import com.user.service.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    CreateUserDto userToCreateUserDto(User user);

    User createUserDtoToUser(CreateUserDto createUserDto);

    UpdateUserDto userToUpdateUserDto(User user);

    User updateUserDtoToUser(UpdateUserDto updateUserDto);


    GetUserDto userToGetUserDto(User user);

    User getUserDtoToUser(GetUserDto getUserDto);


    List<GetUserDto> userListToGetUserDtoList(List<User> userList);

    List<User> getUserDtoListToUserList(List<GetUserDto> getUserDtoList);
}
