package com.example.bakimyonetim.service;

import com.example.bakimyonetim.entity.StockItem;
import java.util.List;

public interface StockItemService {
    StockItem save(StockItem stockItem);
    List<StockItem> findAll();
    StockItem findById(Long id);
    StockItem update(Long id, StockItem stockItem);
    void delete(Long id);
}