package com.example.bakimyonetim.repository;

import com.example.bakimyonetim.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    // E-posta adresine göre kullanıcı bulma (Giriş/Auth işlemleri için)
    Optional<Users> findByEmail(String email);
}