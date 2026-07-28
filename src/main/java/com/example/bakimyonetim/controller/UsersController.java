package com.example.bakimyonetim.controller;

import com.example.bakimyonetim.dto.UserResponse;
import com.example.bakimyonetim.entity.Users;
import com.example.bakimyonetim.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('ADMIN')") // Bu sınıftaki tüm uç noktalar yalnızca ADMIN rolüne özeldir
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping
    public UserResponse create(@Valid @RequestBody Users user) {
        return toResponse(usersService.save(user));
    }

    @GetMapping
    public List<UserResponse> getAll() {
        return usersService.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public UserResponse getById(@PathVariable Long id) {
        return toResponse(usersService.findById(id));
    }

    @PutMapping("/{id}")
    public UserResponse update(@PathVariable Long id, @Valid @RequestBody Users user) {
        return toResponse(usersService.update(id, user));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usersService.delete(id);
    }

    private UserResponse toResponse(Users user) {
        return UserResponse.builder()
                .id(user.getId())
                .kullaniciKodu(user.getKullaniciKodu())
                .ad(user.getAd())
                .soyad(user.getSoyad())
                .email(user.getEmail())
                .rol(user.getRol())
                .factoryEntityId(user.getFactoryEntity() != null ? user.getFactoryEntity().getId() : null)
                .factoryEntityAdi(user.getFactoryEntity() != null ? user.getFactoryEntity().getAd() : null)
                .departmentId(user.getDepartment() != null ? user.getDepartment().getId() : null)
                .departmentAdi(user.getDepartment() != null ? user.getDepartment().getAd() : null)
                .isActive(user.getIsActive())
                .build();
    }
}