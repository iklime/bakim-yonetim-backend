package com.example.bakimyonetim.repository;

import com.example.bakimyonetim.entity.WorkRequestPartUsed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkRequestPartUsedRepository extends JpaRepository<WorkRequestPartUsed, Long> {
}