package com.example. bakimyonetim.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "prediction_result")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PredictionResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ekipman_id", nullable = false)
    private Equipment equipment;

    @Column(name = "ariza_olasiligi")
    private Double arizaOlasiligi;

    @Column(name = "tahmini_ariza_tarihi")
    private LocalDate tahminiArizaTarihi;

    @Column(name = "guven_skoru")
    private Double guvenSkoru;

    @Column(name = "olusturma_tarihi")
    private LocalDateTime olusturmaTarihi = LocalDateTime.now();
}