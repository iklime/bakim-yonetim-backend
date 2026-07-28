package com.example.bakimyonetim.entity;

import com.example.bakimyonetim.enums.FaultCategory;
import com.example.bakimyonetim.enums.PriorityLevel;
import com.example.bakimyonetim.enums.ReportStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;

@Entity
@Table(name = "breakdown_report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BreakdownReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", nullable = false)
    private FactoryEntity factoryEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bildiren_id", nullable = false)
    private Users bildiren;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sorumlu_bakimci_id")
    private Users sorumluBakimci;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FaultCategory kategori;

    @Column(nullable = false, length = 255)
    private String konu;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String aciklama;

    @Column(name = "kapatma_aciklamasi", columnDefinition = "TEXT")
    private String kapatmaAciklamasi;

    @Column(name = "foto_url", length = 255)
    private String fotoUrl;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private PriorityLevel oncelik = PriorityLevel.ACIL;

    @Column(name = "is_declined")
    @Builder.Default
    private Boolean isDeclined = false;

    @Column(name = "olusturma_tarihi", nullable = false)
    @Builder.Default
    private LocalDateTime olusturmaTarihi = LocalDateTime.now();

    @Column(name = "tamamlanma_tarihi")
    private LocalDateTime tamamlanmaTarihi;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private ReportStatus durum = ReportStatus.ACIK; // Veya mevcut varsayılan durumun neyse
}