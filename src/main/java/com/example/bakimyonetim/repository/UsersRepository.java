package com.example.bakimyonetim.repository;

import com.example.bakimyonetim.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}