package com.example.bakimyonetim.service.impl;

import com.example.bakimyonetim.entity.StockItem;
import com.example.bakimyonetim.repository.StockItemRepository;
import com.example.bakimyonetim.service.StockItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockItemServiceImpl implements StockItemService {

    @Autowired
    private StockItemRepository stockItemRepository;

    @Override
    public StockItem save(StockItem stockItem) {
        return stockItemRepository.save(stockItem);
    }

    @Override
    public List<StockItem> findAll() {
        return stockItemRepository.findAll();
    }

    @Override
    public StockItem findById(Long id) {
        return stockItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stok kalemi bulunamadı: " + id));
    }

    @Override
    public StockItem update(Long id, StockItem stockItem) {
        StockItem mevcut = findById(id);
        mevcut.setParcaAdi(stockItem.getParcaAdi());
        mevcut.setParcaKodu(stockItem.getParcaKodu());
        mevcut.setMiktar(stockItem.getMiktar());
        mevcut.setKritikSeviye(stockItem.getKritikSeviye());
        mevcut.setBirimFiyat(stockItem.getBirimFiyat());
        return stockItemRepository.save(mevcut);
    }

    @Override
    public void delete(Long id) {
        stockItemRepository.deleteById(id);
    }
}