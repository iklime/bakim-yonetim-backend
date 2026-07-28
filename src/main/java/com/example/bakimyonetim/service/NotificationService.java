package com.example.bakimyonetim.service;

import com.example.bakimyonetim.dto.NotificationResponse;
import com.example.bakimyonetim.entity.Users;
import com.example.bakimyonetim.enums.NotificationType;
import java.util.List;

public interface NotificationService {
    void create(Users kullanici, NotificationType tip, String baslik, String mesaj, String ilgiliKayitTipi, Long ilgiliKayitId);
    List<NotificationResponse> findForUser(Users user);
    List<NotificationResponse> findUnreadForUser(Users user);
    NotificationResponse markAsRead(Long id, Users user);
}