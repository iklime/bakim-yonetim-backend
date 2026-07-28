package com.example.bakimyonetim.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "prediction_result")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PredictionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "makine_kodu", nullable = false, length = 50)
    private String makineKodu;

    @Column(name = "model_adi", length = 50)
    private String modelAdi; // Örn: LSTM, GRU

    @Column(name = "kalan_gun_tahmini")
    private Double kalanGunTahmini; // Modelin tahmin ettiği arızaya kalan gün sayısı

    @Column(name = "ariza_olasiligi")
    private Double arizaOlasiligi; // Modelin döndürdüğü arıza olasılığı

    @Column(name = "guven_skoru")
    private Double guvenSkoru; // Modelin güven skoru

    @Column(name = "risk_seviyesi", length = 20)
    private String riskSeviyesi; // YÜKSEK, ORTA, DÜŞÜK

    @Column(name = "olusturma_tarihi", nullable = false)
    @Builder.Default
    private LocalDateTime olusturmaTarihi = LocalDateTime.now();

    // Servis katmanında setResult.setEquipment(...) çağrısı ile uyumlu çalışması için:
    public void setEquipment(String equipment) {
        this.makineKodu = equipment;
    }
}