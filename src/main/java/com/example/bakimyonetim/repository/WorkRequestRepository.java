package com.example.bakimyonetim.repository;

import com.example.bakimyonetim.entity.WorkRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRequestRepository extends JpaRepository<WorkRequest, Long> {
    List<WorkRequest> findByFactoryEntityIdOrderByOlusturmaTarihiDesc(Long factoryEntityId);
}
