package com.example.bakimyonetim.service;

import com.example.bakimyonetim.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {
    Attachment uploadAttachment(MultipartFile file, Long userId);
}