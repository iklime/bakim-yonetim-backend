package com.example.bakimyonetim.service;

import com.example.bakimyonetim.dto.AuthResponse;
import com.example.bakimyonetim.dto.LoginRequest;
import com.example.bakimyonetim.dto.RegisterRequest;

public interface AuthService {
    void register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}