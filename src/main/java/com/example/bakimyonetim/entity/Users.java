package com.example.bakimyonetim.entity;

import com.example.bakimyonetim.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Users implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", nullable = false)
    private FactoryEntity factoryEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "kullanici_kodu", unique = true, nullable = false, length = 50)
    private String kullaniciKodu;

    @Column(nullable = false, length = 100)
    private String ad;

    @Column(nullable = false, length = 100)
    private String soyad;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 255)
    private String sifre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole rol;

    @Column(name = "verification_code", length = 10)
    private String verificationCode;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

    @Column(name = "olusturma_tarihi", updatable = false)
    @Builder.Default
    private LocalDateTime olusturmaTarihi = LocalDateTime.now();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }

    @Override
    public String getPassword() {
        return sifre;
    }

    @Override
    public String getUsername() {
        // Not: Tezdeki mimari gereği kullanıcı önce 'kullaniciKodu' girer, ardından mail'e kod gider.
        // Spring Security akışında bunu email veya kullaniciKodu olarak yönetebilirsiniz.
        // Şimdilik e-posta olarak bırakıldı.
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive != null ? this.isActive : false;
    }

    public String getAdSoyad() {
        return ad + " " + soyad;
    }

}