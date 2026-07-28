package com.example.bakimyonetim.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entity_id", nullable = false)
    private FactoryEntity factoryEntity;

    @Column(nullable = false, length = 100)
    private String ad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sef_id")
    private Users sef;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "yonetici_id")
    private Users yonetici;
}