package com.user.service.service.contract;

import com.user.service.entity.LoginRequest;
import com.user.service.entity.RegisterRequest;
import com.user.service.response.AuthResponse;

public interface AuthService {
    AuthResponse login(LoginRequest request);

    AuthResponse register(RegisterRequest request);
}
