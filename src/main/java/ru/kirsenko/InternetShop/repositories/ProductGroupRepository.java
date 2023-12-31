package ru.kirsenko.InternetShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.kirsenko.InternetShop.models.ProductGroup;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public interface ProductGroupRepository  extends JpaRepository<ProductGroup, Long> {

}

