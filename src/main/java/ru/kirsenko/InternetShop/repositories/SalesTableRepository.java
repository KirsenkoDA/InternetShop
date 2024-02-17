package ru.kirsenko.InternetShop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kirsenko.InternetShop.models.Product;
import ru.kirsenko.InternetShop.models.SalesTable;
import ru.kirsenko.InternetShop.models.Status;
import ru.kirsenko.InternetShop.models.User;

public interface SalesTableRepository extends JpaRepository<SalesTable, Long> {
    @Override
    Page<SalesTable> findAll(Pageable pageable);
    Page<SalesTable> findAllByUser(Pageable pageable, User user);
    Page<SalesTable> findByUser(Pageable pageable, User user);
    SalesTable findByStatusAndUser(Status status, User user);
}
