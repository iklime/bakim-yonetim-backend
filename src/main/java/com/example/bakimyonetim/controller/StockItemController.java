package com.example.bakimyonetim.controller;

import com.example.bakimyonetim.entity.StockItem;
import com.example.bakimyonetim.service.StockItemService;
import jakarta.validation.Valid; // Gerekli import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize; // Gerekli import
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-items")
public class StockItemController {

    @Autowired
    private StockItemService stockItemService;

    @PostMapping
    public StockItem create(@Valid @RequestBody StockItem stockItem) { // @Valid eklendi
        return stockItemService.save(stockItem);
    }

    @GetMapping
    public List<StockItem> getAll() {
        return stockItemService.findAll();
    }

    @GetMapping("/{id}")
    public StockItem getById(@PathVariable Long id) {
        return stockItemService.findById(id);
    }

    @PutMapping("/{id}")
    public StockItem update(@PathVariable Long id, @Valid @RequestBody StockItem stockItem) { // @Valid eklendi
        return stockItemService.update(id, stockItem);
    }

    @PreAuthorize("hasRole('ADMIN')") // Silme işlemi sadece ADMIN yetkisine indirgendi
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        stockItemService.delete(id);
    }
}