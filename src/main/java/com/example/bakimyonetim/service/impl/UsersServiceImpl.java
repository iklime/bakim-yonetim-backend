package com.example.bakimyonetim.service.impl;

import com.example.bakimyonetim.entity.Users;
import com.example.bakimyonetim.repository.UsersRepository;
import com.example.bakimyonetim.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users save(Users user) {
        return usersRepository.save(user);
    }

    @Override
    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    @Override
    public Users findById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı: " + id));
    }

    @Override
    public void delete(Long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public Users update(Long id, Users user) {
        Users mevcut = findById(id);
        mevcut.setAd(user.getAd());
        mevcut.setSoyad(user.getSoyad());
        mevcut.setEmail(user.getEmail());
        mevcut.setSifre(user.getSifre());
        mevcut.setKullaniciKodu(user.getKullaniciKodu());
        mevcut.setRol(user.getRol());
        mevcut.setIsActive(user.getIsActive());
        mevcut.setDepartment(user.getDepartment());
        mevcut.setFactoryEntity(user.getFactoryEntity());
        return usersRepository.save(mevcut);
    }
}