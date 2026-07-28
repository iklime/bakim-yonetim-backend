package com.example.bakimyonetim.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "breakdown_employee_log")
public class BreakdownEmployeeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "breakdown_report_id", nullable = false)
    private BreakdownReport breakdownReport;

    @ManyToOne
    @JoinColumn(name = "kullanici_id", nullable = false)
    private Users kullanici;

    @Column(name = "calisma_saati", nullable = false, precision = 5, scale = 2)
    private BigDecimal calismaSaati;


}