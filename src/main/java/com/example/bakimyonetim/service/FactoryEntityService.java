package com.example.bakimyonetim.service;

import com.example.bakimyonetim.entity.FactoryEntity;
import java.util.List;

public interface FactoryEntityService {
    FactoryEntity save(FactoryEntity factoryEntity);
    List<FactoryEntity> findAll();
    FactoryEntity findById(Long id);
    void delete(Long id);
}