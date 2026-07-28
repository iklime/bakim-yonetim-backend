package com.example.bakimyonetim.service.impl;

import com.example.bakimyonetim.entity.Attachment;
import com.example.bakimyonetim.entity.Users; // EKLENDİ
import com.example.bakimyonetim.repository.AttachmentRepository;
import com.example.bakimyonetim.repository.UsersRepository; // EKLENDİ
import com.example.bakimyonetim.service.AttachmentService;
import com.example.bakimyonetim.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService {

    private final FileStorageService fileStorageService;
    private final AttachmentRepository attachmentRepository;
    private final UsersRepository usersRepository; // EKLENDİ

    @Override
    public Attachment uploadAttachment(MultipartFile file, Long userId) {
        // 1. Dosyayı fiziksel olarak kaydet
        String storedFileName = fileStorageService.storeFile(file);

        // 2. Yükleyen kullanıcıyı veritabanından bul (EKLENEN KISIM)
        Users yukleyenKullanici = usersRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));

        // 3. Veritabanı kaydını oluştur
        Attachment attachment = new Attachment();
        attachment.setDosyaAdi(file.getOriginalFilename());
        attachment.setSaklananDosyaAdi(storedFileName);
        attachment.setDosyaYolu("uploads/attachments/" + storedFileName);
        attachment.setMimeType(file.getContentType());
        attachment.setBoyutBytes(file.getSize());

        // DEĞİŞEN KISIM: setYukleyenId yerine setYukleyen kullanıp nesneyi veriyoruz
        attachment.setYukleyen(yukleyenKullanici);

        return attachmentRepository.save(attachment);
    }
}