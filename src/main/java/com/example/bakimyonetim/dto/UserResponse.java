package com.example.bakimyonetim.dto;

import com.example.bakimyonetim.enums.UserRole;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {
    private Long id;
    private String kullaniciKodu;
    private String ad;
    private String soyad;
    private String email;
    private UserRole rol;
    private Long factoryEntityId;
    private String factoryEntityAdi;
    private Long departmentId;
    private String departmentAdi;
    private Boolean isActive;
}
