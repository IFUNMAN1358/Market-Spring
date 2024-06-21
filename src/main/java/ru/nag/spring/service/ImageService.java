package ru.nag.spring.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    private final String uploadDir = "src/main/resources/product_img/";

    public String saveImage(MultipartFile imageFile) {
        try {
            if (imageFile.isEmpty()) {
                throw new IOException("File is empty");
            }

            String fileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);

            Files.copy(imageFile.getInputStream(), filePath);

            return "/resources/product_img/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image file", e);
        }
    }

    public void deleteProductImageUrl(String imageUrl) {
        try {
            String fileName = Paths.get(imageUrl).getFileName().toString();
            Path filePath = Paths.get(uploadDir, fileName);

            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete image file", e);
        }
    }
}
