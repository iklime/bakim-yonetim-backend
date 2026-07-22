package com.example.bakimyonetim.entity;

import com.example.bakimyonetim.enums.FaultCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "fault_code")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FaultCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String kod;

    @Column(nullable = false, length = 255)
    private String aciklama;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FaultCategory kategori;
}