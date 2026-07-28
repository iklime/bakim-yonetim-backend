package com.example.bakimyonetim.repository;

import com.example.bakimyonetim.entity.PeriodicCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodicCheckRepository extends JpaRepository<PeriodicCheck, Long> {
}