package com.example.bakimyonetim.entity;

import com.example.bakimyonetim.enums.MaintenanceType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "maintenance_record")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ekipman_id", nullable = false)
    private Equipment equipment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teknisyen_id")
    private Users teknisyen;

    @Column(nullable = false)
    private LocalDateTime tarih;

    @Enumerated(EnumType.STRING)
    private MaintenanceType tip;

    @Column(columnDefinition = "TEXT")
    private String aciklama;
}