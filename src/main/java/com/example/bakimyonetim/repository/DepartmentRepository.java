package com.example.bakimyonetim.repository;

import com.example.bakimyonetim.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}