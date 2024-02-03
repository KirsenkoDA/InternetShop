package ru.kirsenko.InternetShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kirsenko.InternetShop.models.Characteristic;
import ru.kirsenko.InternetShop.models.Product;
import ru.kirsenko.InternetShop.models.ProductCharacteristic;
import ru.kirsenko.InternetShop.models.ProductGroup;

import java.util.List;

public interface ProductCharacteristicRepository  extends JpaRepository<ProductCharacteristic, Long > {
    List<ProductCharacteristic> findProductCharacteristicByProductAndCharacteristic(Product product, Characteristic characteristic);
    void deleteAllByProductId(Long id);
    List<ProductCharacteristic> findByProduct_Id(Long id);
}
