package com.example.bakimyonetim.service.impl;

import com.example.bakimyonetim.entity.FactoryEntity;
import com.example.bakimyonetim.repository.FactoryEntityRepository;
import com.example.bakimyonetim.service.FactoryEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryEntityServiceImpl implements FactoryEntityService {

    @Autowired
    private FactoryEntityRepository factoryEntityRepository;

    @Override
    public FactoryEntity save(FactoryEntity factoryEntity) {
        return factoryEntityRepository.save(factoryEntity);
    }

    @Override
    public List<FactoryEntity> findAll() {
        return factoryEntityRepository.findAll();
    }

    @Override
    public FactoryEntity findById(Long id) {
        return factoryEntityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lokasyon bulunamadı: " + id));
    }

    @Override
    public void delete(Long id) {
        factoryEntityRepository.deleteById(id);
    }
}