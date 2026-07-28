package com.example.bakimyonetim.dto;

import com.example.bakimyonetim.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponse {

    private String token;
    private String email;
    private String adSoyad; // Frontend'in kullanıcıyı karşılaması için profesyonel dokunuş
    private UserRole role;   // String yerine Enum kullanarak tip güvenliği sağlıyoruz
}