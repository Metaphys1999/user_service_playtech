package com.user.service.service.impl;

import com.user.service.entity.LoginRequest;
import com.user.service.entity.RegisterRequest;
import com.user.service.entity.User;
import com.user.service.repository.UserRepository;
import com.user.service.response.AuthResponse;
import com.user.service.response.UserResponse;
import com.user.service.role.Role;
import com.user.service.security.jwt.JwtService;
import com.user.service.service.contract.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getPhone(), request.getPassword()));
        UserDetails userDetails  = userRepository.findByPhone(request.getPhone()).orElseThrow();

        String token = jwtService.getToken(userDetails );

        UserResponse userResponse = new UserResponse();
        User user = (User) userDetails;
        userResponse.setUserId(user.getUserId());
        userResponse.setPhone(user.getPhone());
        userResponse.setPassword(user.getPassword());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setDateBirth(user.getDateBirth());
        userResponse.setAddress(user.getAddress());
        userResponse.setEmail(user.getEmail());
        userResponse.setRole(user.getRole());

        AuthResponse authResponse = new AuthResponse();
        authResponse.setUser(userResponse);
        authResponse.setToken(token);

        return authResponse;
    }

    // Método register solo necesario para setear un primer registro de usuario para hacer uso de los endpoints con seguridad

    @Override
    public AuthResponse register(RegisterRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateBirth(request.getDateBirth());
        user.setAddress(request.getAddress());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setEmail(request.getEmail());
        user.setRole(Role.USER);

        userRepository.save(user);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(jwtService.getToken(user));

        return authResponse;
    }
}
