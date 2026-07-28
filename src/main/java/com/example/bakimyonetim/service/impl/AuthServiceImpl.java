package com.example.bakimyonetim.service.impl;

import com.example.bakimyonetim.dto.AuthResponse;
import com.example.bakimyonetim.dto.LoginRequest;
import com.example.bakimyonetim.dto.RegisterRequest;
import com.example.bakimyonetim.entity.FactoryEntity;
import com.example.bakimyonetim.entity.Users;
import com.example.bakimyonetim.repository.FactoryEntityRepository;
import com.example.bakimyonetim.repository.UsersRepository;
import com.example.bakimyonetim.security.JwtService;
import com.example.bakimyonetim.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsersRepository usersRepository;
    private final FactoryEntityRepository factoryEntityRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional // İşlem sırasında hata olursa veritabanını geri alır (Rollback)
    public void register(RegisterRequest request) {

        // 1. E-posta kullanımda mı kontrolü
        if (usersRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Bu e-posta adresi zaten kullanılıyor.");
        }

        // 2. Tesis/Fabrika var mı kontrolü
        FactoryEntity factory = factoryEntityRepository.findById(request.getEntityId())
                .orElseThrow(() -> new RuntimeException("Belirtilen tesis bulunamadı."));

        // 3. Güvenli DTO'dan Veritabanı Entity'sine dönüşüm (Mapping)
        // 3. Güvenli DTO'dan Veritabanı Entity'sine dönüşüm (Mapping)
        Users newUser = new Users();

// DEĞİŞEN KISIM: setentity_id yerine setFactoryEntity kullanıp doğrudan factory nesnesini veriyoruz
        newUser.setFactoryEntity(factory);

        newUser.setKullaniciKodu(request.getKullaniciKodu());
        newUser.setAd(request.getAd());
        newUser.setSoyad(request.getSoyad());
        newUser.setEmail(request.getEmail());
        newUser.setRol(request.getRol());
        newUser.setIsActive(true);

        // Şifreyi şifreleyerek kaydediyoruz
        newUser.setSifre(passwordEncoder.encode(request.getSifre()));

        // 4. Veritabanına kaydet
        usersRepository.save(newUser);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        // 1. Spring Security ile kullanıcı doğrulaması (Email ve Şifre eşleşiyor mu?)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSifre())
        );

        // 2. Doğrulama başarılıysa kullanıcıyı veritabanından getir
        Users user = usersRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));

        // 3. JWT Token üret
        String token = jwtService.generateToken(user.getEmail(), user.getRol().name());

        // 4. İsim ve Soyismi birleştir
        String adSoyad = user.getAd() + " " + user.getSoyad();

        // 5. Yanıtı dön
        return AuthResponse.builder()
                .token(token)
                .email(user.getEmail())
                .adSoyad(adSoyad)
                .role(user.getRol())
                .build();
    }
}