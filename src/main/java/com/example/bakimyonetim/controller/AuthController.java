package com.example.bakimyonetim.controller;

import com.example.bakimyonetim.dto.AuthResponse;
import com.example.bakimyonetim.dto.LoginRequest;
import com.example.bakimyonetim.dto.RegisterRequest; // KENDİN OLUŞTURACAKSIN
import com.example.bakimyonetim.service.AuthService; // KENDİN OLUŞTURACAKSIN
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // Artık Repository veya PasswordEncoder'ı Controller'da çağırmıyoruz!
    // Tüm yükü AuthService'e devrediyoruz.
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request) {
        // Doğrudan Users entity'si değil, güvenli RegisterRequest DTO'su alıyoruz
        authService.register(request);
        return new ResponseEntity<>("Kullanıcı başarıyla kaydedildi.", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        // Tüm kimlik doğrulama ve token üretme işi Servis'te yapılıyor
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}