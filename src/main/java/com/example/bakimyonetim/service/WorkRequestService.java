package com.example.bakimyonetim.service;

import com.example.bakimyonetim.dto.WorkRequestCreateRequest;
import com.example.bakimyonetim.dto.WorkRequestResponse;
import com.example.bakimyonetim.entity.Users;

import java.util.List;

public interface WorkRequestService {
    WorkRequestResponse create(WorkRequestCreateRequest request, Users olusturan);
    List<WorkRequestResponse> findAllForUser(Users user);
    WorkRequestResponse findByIdForUser(Long id, Users user);
    WorkRequestResponse approve(Long id, String aciklama, Users user);
    WorkRequestResponse complete(Long id, String aciklama, Users user);
    WorkRequestResponse confirmCompletion(Long id, String aciklama, Users user);
    WorkRequestResponse rejectOrReturn(Long id, String aciklama, Users user);
}
