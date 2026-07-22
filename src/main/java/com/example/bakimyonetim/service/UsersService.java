package com.example.bakimyonetim.service;

import com.example.bakimyonetim.entity.Users;
import java.util.List;

public interface UsersService {
    Users save(Users user);
    List<Users> findAll();
    Users findById(Long id);
    void delete(Long id);
}
