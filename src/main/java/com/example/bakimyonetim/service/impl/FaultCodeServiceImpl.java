package com.example.bakimyonetim.service.impl;

import com.example.bakimyonetim.entity.FaultCode;
import com.example.bakimyonetim.repository.FaultCodeRepository;
import com.example.bakimyonetim.service.FaultCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FaultCodeServiceImpl implements FaultCodeService {

    @Autowired
    private FaultCodeRepository faultCodeRepository;

    @Override
    public FaultCode save(FaultCode faultCode) {
        return faultCodeRepository.save(faultCode);
    }

    @Override
    public List<FaultCode> findAll() {
        return faultCodeRepository.findAll();
    }

    @Override
    public FaultCode findById(Long id) {
        return faultCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Arıza kodu bulunamadı: " + id));
    }

    @Override
    public void delete(Long id) {
        faultCodeRepository.deleteById(id);
    }
}