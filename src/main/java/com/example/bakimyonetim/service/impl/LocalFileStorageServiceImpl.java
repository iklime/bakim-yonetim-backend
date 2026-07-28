package com.example.bakimyonetim.service.impl;

import com.example.bakimyonetim.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class LocalFileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;

    // application.properties dosyasında file.upload-dir ayarı yoksa varsayılan klasörü kullanır
    public LocalFileStorageServiceImpl(@Value("${file.upload-dir:uploads/attachments}") String uploadDir) {
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Dosya yükleme klasörü oluşturulamadı.", ex);
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // İsim çakışmalarını önlemek için başına 8 haneli rastgele bir metin ekliyoruz
            String randomString = UUID.randomUUID().toString().substring(0, 8);
            String uniqueFileName = randomString + "_" + originalFileName;

            // Dosyayı sunucudaki fiziksel klasöre kopyalıyoruz
            Path targetLocation = this.fileStorageLocation.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return uniqueFileName;
        } catch (IOException ex) {
            throw new RuntimeException("Dosya kaydedilemedi: " + originalFileName, ex);
        }
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();

            if (Files.exists(filePath)) {
                return new UrlResource(filePath.toUri());
            } else {
                throw new FileNotFoundException("Dosya bulunamadı: " + fileName);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Dosya indirme hatası: " + fileName, ex);
        }
    }
}