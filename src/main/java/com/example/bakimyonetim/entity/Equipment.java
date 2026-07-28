package com.example.bakimyonetim.entity;

import com.example.bakimyonetim.enums.EquipmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDate;

@Entity
@Table(name = "equipment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", nullable = false)
    private FactoryEntity factoryEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "makine_numarasi", unique = true, nullable = false, length = 50)
    private String makineNumarasi;

    @Column(nullable = false, length = 100)
    private String ad;

    @Enumerated(EnumType.STRING)
    @Column(name = "durum")
    @Builder.Default
    private EquipmentStatus durum = EquipmentStatus.AKTIF;

    @Column(name = "kurulum_tarihi")
    private LocalDate kurulumTarihi;

    @Column(name = "son_bakim_tarihi")
    private LocalDate sonBakimTarihi; // Son bakım tarihi alanı eklendi

    @Column(name = "is_periodic_time_came")
    @Builder.Default
    private Boolean isPeriodicTimeCame = false;

    @Column(name = "period_day_left")
    private Integer periodDayLeft;
}