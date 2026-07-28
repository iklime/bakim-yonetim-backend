package com.example.bakimyonetim.service;

import com.example.bakimyonetim.entity.Department;
import java.util.List;

public interface DepartmentService {
    Department save(Department department);
    List<Department> findAll();
    Department findById(Long id);
    Department update(Long id, Department department);
    void delete(Long id);
}