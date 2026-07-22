package com.example.bakimyonetim.controller;

import com.example.bakimyonetim.dto.PredictionRequestDto;
import com.example.bakimyonetim.entity.PredictionResult;
import com.example.bakimyonetim.service.PredictionResultService;
import com.example.bakimyonetim.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predictions")
public class PredictionResultController {

    @Autowired
    private PredictionResultService predictionResultService;

    @Autowired
    private PredictionService predictionService;

    @PostMapping
    public PredictionResult create(@RequestBody PredictionResult result) {
        return predictionResultService.save(result);
    }

    @GetMapping
    public List<PredictionResult> getAll() {
        return predictionResultService.findAll();
    }

    @GetMapping("/{id}")
    public PredictionResult getById(@PathVariable Long id) {
        return predictionResultService.findById(id);
    }

    @PostMapping("/generate/{equipmentId}")
    public PredictionResult generate(@PathVariable Long equipmentId, @RequestBody PredictionRequestDto request) {
        return predictionService.generatePrediction(
                equipmentId,
                request.getTip(),
                request.getHavaSicakligi(),
                request.getIslemSicakligi(),
                request.getDonusHizi(),
                request.getTork(),
                request.getAletAsinmasi()
        );
    }
}