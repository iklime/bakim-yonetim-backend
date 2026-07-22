package com.example.bakimyonetim.entity;

import com.example.bakimyonetim.enums.PriorityLevel;
import com.example.bakimyonetim.enums.ReportStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "breakdown_report")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BreakdownReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ekipman_id", nullable = false)
    private Equipment equipment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bildiren_kullanici_id")
    private Users bildirenKullanici;

    @Column(nullable = false)
    private LocalDateTime tarih;

    @Column(columnDefinition = "TEXT")
    private String aciklama;

    @Column(name = "foto_url")
    private String fotoUrl;

    @Enumerated(EnumType.STRING)
    private PriorityLevel oncelik;

    @Enumerated(EnumType.STRING)
    private ReportStatus durum = ReportStatus.BEKLEMEDE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fault_code_id")
    private FaultCode faultCode;
}