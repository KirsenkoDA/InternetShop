package ru.kirsenko.InternetShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kirsenko.InternetShop.models.Characteristic;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
}
