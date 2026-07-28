package com.example.bakimyonetim.dto;

import com.example.bakimyonetim.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotNull(message = "Tesis/Fabrika ID zorunludur")
    private Long entityId;

    @NotBlank(message = "Kullanıcı kodu boş bırakılamaz")
    private String kullaniciKodu;

    @NotBlank(message = "Ad boş bırakılamaz")
    private String ad;

    @NotBlank(message = "Soyad boş bırakılamaz")
    private String soyad;

    @NotBlank(message = "E-posta alanı boş bırakılamaz")
    @Email(message = "Geçerli bir e-posta adresi giriniz")
    private String email;

    @NotBlank(message = "Şifre boş bırakılamaz")
    @Size(min = 6, message = "Şifre en az 6 karakter olmalıdır")
    private String sifre;

    @NotNull(message = "Kullanıcı rolü belirtilmelidir")
    private UserRole rol;
}
