package com.example.bakimyonetim.service.impl;

import com.example.bakimyonetim.dto.MlPredictionRequest;
import com.example.bakimyonetim.dto.MlPredictionResponse;
import com.example.bakimyonetim.entity.Equipment;
import com.example.bakimyonetim.entity.PredictionResult;
import com.example.bakimyonetim.repository.EquipmentRepository;
import com.example.bakimyonetim.repository.PredictionResultRepository;
import com.example.bakimyonetim.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class PredictionServiceImpl implements PredictionService {

    private static final String ML_SERVICE_URL = "http://localhost:8000/tahmin";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private PredictionResultRepository predictionResultRepository;

    @Override
    public PredictionResult generatePrediction(Long equipmentId, String tip, Double havaSicakligi,
                                               Double islemSicakligi, Double donusHizi,
                                               Double tork, Double aletAsinmasi) {

        Equipment equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new RuntimeException("Ekipman bulunamadı: " + equipmentId));

        MlPredictionRequest request = new MlPredictionRequest(
                tip, havaSicakligi, islemSicakligi, donusHizi, tork, aletAsinmasi
        );

        MlPredictionResponse response = restTemplate.postForObject(ML_SERVICE_URL, request, MlPredictionResponse.class);

        if (response == null) {
            throw new RuntimeException("ML servisinden yanıt alınamadı");
        }

        PredictionResult result = new PredictionResult();
        result.setEquipment(String.valueOf(equipment));
        result.setArizaOlasiligi(response.getAriza_olasiligi());
        result.setGuvenSkoru(response.getGuven_skoru());
        result.setOlusturmaTarihi(LocalDateTime.now());

        return predictionResultRepository.save(result);
    }
}