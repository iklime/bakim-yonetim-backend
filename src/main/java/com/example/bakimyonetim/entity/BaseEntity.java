package com.example.bakimyonetim.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity {

    @Column(name = "olusturma_tarihi", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime olusturmaTarihi;

    @Column(name = "guncelleme_tarihi", nullable = false)
    @UpdateTimestamp
    private LocalDateTime guncellemeTarihi;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}