package ru.nag.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
public class ImageController {

    @GetMapping("/resources/product_img/{filename:.+}")
    public ResponseEntity<Resource> getProductImage(@PathVariable String filename) throws Exception {
        String imageDirectory = "src/main/resources/product_img/";
        Path imagePath = Paths.get(imageDirectory).resolve(filename).normalize();
        Resource resource = new UrlResource(imagePath.toUri());

        if (resource.exists() && resource.isReadable()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(resource);
        } else {
            throw new Exception("Could not load image: " + filename);
        }
    }

}
