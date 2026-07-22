package com.example.bakimyonetim.controller;

import com.example.bakimyonetim.entity.FaultCode;
import com.example.bakimyonetim.service.FaultCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fault-codes")
public class FaultCodeController {

    @Autowired
    private FaultCodeService faultCodeService;

    @PostMapping
    public FaultCode create(@RequestBody FaultCode faultCode) {
        return faultCodeService.save(faultCode);
    }

    @GetMapping
    public List<FaultCode> getAll() {
        return faultCodeService.findAll();
    }

    @GetMapping("/{id}")
    public FaultCode getById(@PathVariable Long id) {
        return faultCodeService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        faultCodeService.delete(id);
    }
}