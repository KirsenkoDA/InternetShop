package ru.kirsenko.InternetShop.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.kirsenko.InternetShop.models.SalesLine;
import ru.kirsenko.InternetShop.models.SalesTable;
import ru.kirsenko.InternetShop.models.User;

public interface SalesLineRepository  extends JpaRepository<SalesLine, Long> {
    @Override
    Page<SalesLine> findAll(Pageable pageable);
    Page<SalesLine> findBySalesTable(Pageable pageable, SalesTable salesTable);
}
