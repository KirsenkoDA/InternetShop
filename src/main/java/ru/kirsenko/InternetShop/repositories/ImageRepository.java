package ru.kirsenko.InternetShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kirsenko.InternetShop.models.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
