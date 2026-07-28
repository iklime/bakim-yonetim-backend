package com.example.bakimyonetim.controller;

import com.example.bakimyonetim.entity.Department;
import com.example.bakimyonetim.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Department create(@RequestBody Department department) {
        return departmentService.save(department);
    }

    @GetMapping
    public List<Department> getAll() {
        return departmentService.findAll();
    }

    @GetMapping("/{id}")
    public Department getById(@PathVariable Long id) {
        return departmentService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Department update(@PathVariable Long id, @RequestBody Department department) {
        return departmentService.update(id, department);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        departmentService.delete(id);
    }
}