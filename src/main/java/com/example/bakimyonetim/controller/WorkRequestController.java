package com.example.bakimyonetim.controller;

import com.example.bakimyonetim.dto.WorkRequestCreateRequest;
import com.example.bakimyonetim.dto.WorkRequestResponse;
import com.example.bakimyonetim.dto.WorkRequestActionRequest;
import com.example.bakimyonetim.entity.Users;
import com.example.bakimyonetim.service.WorkRequestService;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/work-requests")
public class WorkRequestController {

    private final WorkRequestService workRequestService;

    public WorkRequestController(WorkRequestService workRequestService) {
        this.workRequestService = workRequestService;
    }

    @PostMapping
    public WorkRequestResponse create(@Valid @RequestBody WorkRequestCreateRequest request,
                                      @AuthenticationPrincipal Users currentUser) {
        return workRequestService.create(request, currentUser);
    }

    @GetMapping
    public List<WorkRequestResponse> getAll(@AuthenticationPrincipal Users currentUser) {
        return workRequestService.findAllForUser(currentUser);
    }

    @GetMapping("/{id}")
    public WorkRequestResponse getById(@PathVariable Long id,
                                       @AuthenticationPrincipal Users currentUser) {
        return workRequestService.findByIdForUser(id, currentUser);
    }

    @PostMapping("/{id}/approve")
    public WorkRequestResponse approve(@PathVariable Long id, @Valid @RequestBody WorkRequestActionRequest request, @AuthenticationPrincipal Users currentUser) {
        return workRequestService.approve(id, request.getAciklama(), currentUser);
    }

    @PostMapping("/{id}/complete")
    public WorkRequestResponse complete(@PathVariable Long id, @Valid @RequestBody WorkRequestActionRequest request, @AuthenticationPrincipal Users currentUser) {
        return workRequestService.complete(id, request.getAciklama(), currentUser);
    }

    @PostMapping("/{id}/confirm")
    public WorkRequestResponse confirm(@PathVariable Long id, @Valid @RequestBody WorkRequestActionRequest request, @AuthenticationPrincipal Users currentUser) {
        return workRequestService.confirmCompletion(id, request.getAciklama(), currentUser);
    }

    @PostMapping("/{id}/reject")
    public WorkRequestResponse reject(@PathVariable Long id, @Valid @RequestBody WorkRequestActionRequest request, @AuthenticationPrincipal Users currentUser) {
        return workRequestService.rejectOrReturn(id, request.getAciklama(), currentUser);
    }
}
