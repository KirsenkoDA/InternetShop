package ru.kirsenko.InternetShop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kirsenko.InternetShop.models.Characteristic;
import ru.kirsenko.InternetShop.models.ImageLob;
import ru.kirsenko.InternetShop.repositories.ImageLobRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class ImageLobService {
    private final ImageLobRepository imageLobRepository;
    public void save(ImageLob imageLob){
        imageLobRepository.save(imageLob);
    }
}
