package ru.kirsenko.InternetShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kirsenko.InternetShop.models.SalesTable;
import ru.kirsenko.InternetShop.models.Status;

public interface StatusRepository  extends JpaRepository<Status, Long> {
}
