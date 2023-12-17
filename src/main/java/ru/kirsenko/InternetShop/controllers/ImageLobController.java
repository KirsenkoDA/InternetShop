package ru.kirsenko.InternetShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.kirsenko.InternetShop.models.Image;
import ru.kirsenko.InternetShop.models.ImageLob;
import ru.kirsenko.InternetShop.repositories.ImageLobRepository;
import ru.kirsenko.InternetShop.repositories.ImageRepository;

import java.io.ByteArrayInputStream;
@RestController
@RequiredArgsConstructor
public class ImageLobController {
    private final ImageRepository imageRepository;
    private final ImageLobRepository imageLobRepository;
    @GetMapping("/imagesLob/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id)
    {
        Image image = imageRepository.findById(id).orElse(null);
        ImageLob imageLob = imageLobRepository.findById(id).orElse(null);
        return ResponseEntity.ok()
                .header("fileName", image.getOriginalFileName())
                .contentType(MediaType.valueOf(image.getContentType()))
                .contentLength(image.getSize())
                .body(new InputStreamResource(new ByteArrayInputStream(imageLob.getBytes())));
    }
}
