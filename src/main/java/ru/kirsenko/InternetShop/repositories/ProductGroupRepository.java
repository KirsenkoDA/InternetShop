package ru.kirsenko.InternetShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kirsenko.InternetShop.models.ProductGroup;

import java.util.List;

public interface ProductGroupRepository  extends JpaRepository<ProductGroup, Long> {
}
