package com.example.bakimyonetim.controller;

import com.example.bakimyonetim.dto.AuthResponse;
import com.example.bakimyonetim.dto.LoginRequest;
import com.example.bakimyonetim.entity.Users;
import com.example.bakimyonetim.repository.UsersRepository;
import com.example.bakimyonetim.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        user.setSifre(passwordEncoder.encode(user.getSifre()));
        return usersRepository.save(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSifre())
        );

        Users user = usersRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(request.getEmail()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));

        String token = jwtService.generateToken(user.getEmail(), user.getRol().name());

        return new AuthResponse(token, user.getEmail(), user.getRol().name());
    }
}