package com.example.bakimyonetim.entity;

import com.example.bakimyonetim.enums.EquipmentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "equipment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String ad;

    @Column(length = 100)
    private String tip;

    @Column(length = 100)
    private String konum;

    @Column(name = "kurulum_tarihi")
    private LocalDate kurulumTarihi;

    @Enumerated(EnumType.STRING)
    @Column(name = "durum")
    private EquipmentStatus durum = EquipmentStatus.AKTIF;

    @Column(name = "son_bakim_tarihi")
    private LocalDate sonBakimTarihi;
}