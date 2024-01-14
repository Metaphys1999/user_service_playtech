package com.user.service.service.impl;

import com.user.service.dto.CreateUserDto;
import com.user.service.dto.GetUserDto;
import com.user.service.dto.UpdateUserDto;
import com.user.service.entity.User;
import com.user.service.mapper.UserMapper;
import com.user.service.repository.UserRepository;
import com.user.service.service.contract.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Override
    public CreateUserDto createUser(CreateUserDto createUserDto) {
        logger.info("Create User");

        User newUser = userMapper.createUserDtoToUser(createUserDto);
        newUser = userRepository.save(newUser);

        CreateUserDto newCreateUserDto = userMapper.userToCreateUserDto(newUser);

        return newCreateUserDto;
    }

    @Override
    public UpdateUserDto updateUser(Integer userId, UpdateUserDto updateUserDto) {
        logger.info("Update User");

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("The user with ID: " + userId + " was not found."));

        existingUser.setFirstName(updateUserDto.getFirstName());
        existingUser.setLastName(updateUserDto.getLastName());
        existingUser.setDateBirth(updateUserDto.getDateBirth());
        existingUser.setAddress(updateUserDto.getAddress());
        existingUser.setPhone(updateUserDto.getPhone());
        existingUser.setEmail(updateUserDto.getEmail());

        User updatedUser = userRepository.save(existingUser);

        UpdateUserDto updatedUserDto = userMapper.userToUpdateUserDto(updatedUser);

        return updatedUserDto;
    }

    @Override
    public String deleteUser(Integer userId) {
        logger.info("Delete User");

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("The user with ID: " + userId + " was not found."));

        userRepository.delete(existingUser);

        return "User with ID: " + userId + " has been deleted.";
    }

    @Override
    public GetUserDto getUser(Integer userId) {
        logger.info("Get User By ID");

        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException
                        ("The user with ID: " + userId + " was not found."));

        GetUserDto clientDto = userMapper.userToGetUserDto(existingUser);

        return clientDto;
    }

    @Override
    public List<GetUserDto> getUsers() {
        logger.info("Get All Users");

        List<User> clientList = userRepository.findAll();
        List<GetUserDto> clientDtoList = userMapper.userListToGetUserDtoList(clientList);

        return clientDtoList;
    }
}
