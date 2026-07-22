package com.example.bakimyonetim.controller;

import com.example.bakimyonetim.entity.StockItem;
import com.example.bakimyonetim.service.StockItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-items")
public class StockItemController {

    @Autowired
    private StockItemService stockItemService;

    @PostMapping
    public StockItem create(@RequestBody StockItem stockItem) {
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
    public StockItem update(@PathVariable Long id, @RequestBody StockItem stockItem) {
        return stockItemService.update(id, stockItem);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        stockItemService.delete(id);
    }
}