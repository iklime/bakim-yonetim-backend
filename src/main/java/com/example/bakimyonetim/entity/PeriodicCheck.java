package com.example.bakimyonetim.entity;

import com.example.bakimyonetim.enums.FrequencyType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "periodic_check")
public class PeriodicCheck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "entity_id", nullable = false)
    private FactoryEntity factoryEntity;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "sorumlu_id")
    private Users sorumlu;

    @Column(name = "frequency_time", nullable = false)
    private Integer frequencyTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "frequency_type", nullable = false)
    private FrequencyType frequencyType;

    @Column(name = "son_bakim_tarihi")
    private LocalDate sonBakimTarihi;

    @Column(name = "bir_sonraki_bakim_tarihi")
    private LocalDate birSonrakiBakimTarihi;

    private String aciklama;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;
}