package com.user.service.controller;

import com.user.service.dto.CreateUserDto;
import com.user.service.dto.GetUserDto;
import com.user.service.dto.UpdateUserDto;
import com.user.service.entity.LoginRequest;
import com.user.service.entity.User;
import com.user.service.response.AuthResponse;
import com.user.service.service.contract.AuthService;
import com.user.service.service.contract.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping
    public ResponseEntity<CreateUserDto> createUser(@RequestBody CreateUserDto createUserDto) {
        return new ResponseEntity<>(userService.createUser(createUserDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id_user}")
    public ResponseEntity<UpdateUserDto> updateUser(@PathVariable("id_user") Integer userId, @RequestBody UpdateUserDto updateUserDto) {
        UpdateUserDto updatedUserDto = userService.updateUser(userId, updateUserDto);

        if (updatedUserDto != null) {
            return ResponseEntity.ok(updatedUserDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id_user}")
    public ResponseEntity<String> deleteUser(@PathVariable("id_user") Integer userId) {
        String deletedMessage = userService.deleteUser(userId);

        return ResponseEntity.ok(deletedMessage);
    }

    @GetMapping
    public ResponseEntity<List<GetUserDto>> getUsers() {
        List<GetUserDto> getUserDtoList = userService.getUsers();
        if (getUserDtoList.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(getUserDtoList);
    }

    @GetMapping("{user_id}")
    public ResponseEntity<GetUserDto> getUser(@PathVariable("user_id") Integer userId) {
        GetUserDto getUserDto = userService.getUser(userId);
        if (getUserDto == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(getUserDto);
    }


}
