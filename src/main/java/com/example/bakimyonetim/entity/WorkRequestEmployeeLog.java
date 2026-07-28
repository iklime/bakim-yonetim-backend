package com.example.bakimyonetim.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "work_request_employee_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkRequestEmployeeLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_request_id", nullable = false)
    private WorkRequest workRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kullanici_id", nullable = false)
    private Users kullanici;

    @Column(name = "calisma_saati", nullable = false, precision = 6, scale = 2)
    private BigDecimal calismaSaati;

    @Column(name = "olusturma_tarihi", nullable = false)
    @Builder.Default
    private LocalDateTime olusturmaTarihi = LocalDateTime.now();
}
