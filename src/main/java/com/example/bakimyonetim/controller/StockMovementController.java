package com.example.bakimyonetim.controller;

import com.example.bakimyonetim.dto.StockMovementResponse;
import com.example.bakimyonetim.service.StockMovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-movements")
public class StockMovementController {

    @Autowired
    private StockMovementService stockMovementService;

    @GetMapping("/stock-item/{stockItemId}")
    public List<StockMovementResponse> getByStockItem(@PathVariable Long stockItemId) {
        return stockMovementService.findByStockItem(stockItemId);
    }
}