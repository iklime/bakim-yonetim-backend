package com.example.bakimyonetim.controller;

import com.example.bakimyonetim.dto.NotificationResponse;
import com.example.bakimyonetim.entity.Users;
import com.example.bakimyonetim.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public List<NotificationResponse> getMine(@AuthenticationPrincipal Users user) {
        return notificationService.findForUser(user);
    }

    @GetMapping("/unread")
    public List<NotificationResponse> getUnread(@AuthenticationPrincipal Users user) {
        return notificationService.findUnreadForUser(user);
    }

    @PutMapping("/{id}/read")
    public NotificationResponse markAsRead(@PathVariable Long id, @AuthenticationPrincipal Users user) {
        return notificationService.markAsRead(id, user);
    }
}
