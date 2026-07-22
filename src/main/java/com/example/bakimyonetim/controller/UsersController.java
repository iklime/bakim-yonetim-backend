package com.example.bakimyonetim.controller;

import com.example.bakimyonetim.entity.Users;
import com.example.bakimyonetim.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping
    public Users create(@RequestBody Users user) {
        return usersService.save(user);
    }

    @GetMapping
    public List<Users> getAll() {
        return usersService.findAll();
    }

    @GetMapping("/{id}")
    public Users getById(@PathVariable Long id) {
        return usersService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usersService.delete(id);
    }
}