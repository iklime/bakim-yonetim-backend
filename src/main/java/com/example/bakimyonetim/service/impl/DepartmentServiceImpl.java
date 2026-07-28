package com.example.bakimyonetim.service.impl;

import com.example.bakimyonetim.entity.Department;
import com.example.bakimyonetim.repository.DepartmentRepository;
import com.example.bakimyonetim.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bölüm bulunamadı: " + id));
    }

    @Override
    public Department update(Long id, Department department) {
        Department mevcut = findById(id);
        mevcut.setAd(department.getAd());
        mevcut.setFactoryEntity(department.getFactoryEntity());
        mevcut.setSef(department.getSef());
        mevcut.setYonetici(department.getYonetici());
        return departmentRepository.save(mevcut);
    }

    @Override
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }
}