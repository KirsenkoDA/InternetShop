package ru.kirsenko.InternetShop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.type.descriptor.sql.NCharTypeDescriptor;
import org.springframework.stereotype.Service;
import ru.kirsenko.InternetShop.models.Characteristic;
import ru.kirsenko.InternetShop.models.Product;
import ru.kirsenko.InternetShop.models.ProductCharacteristic;
import ru.kirsenko.InternetShop.repositories.ProductCharacteristicRepository;

import java.util.List;

import static org.hibernate.internal.util.collections.ArrayHelper.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductCharacteristicService {
    private final ProductCharacteristicRepository productCharacteristicRepository;
    public void save(ProductCharacteristic productCharacteristic){
        productCharacteristicRepository.save(productCharacteristic);
    }
    public void delete(Long id)
    {
        productCharacteristicRepository.deleteById(id);
    }
    public List<ProductCharacteristic> findProductCharacteristic(Product product, Characteristic characteristic) {
        return productCharacteristicRepository.findProductCharacteristicByProductAndCharacteristic(product, characteristic);
    }
    public ProductCharacteristic show(Long id)
    {
        return productCharacteristicRepository.findById(id).orElse(null);
    }
    public void deleteIfExist(Product product, Characteristic characteristic)
    {
        productCharacteristicRepository.findProductCharacteristicByProductAndCharacteristic(product, characteristic);
        ProductCharacteristic productCharacteristicParm = productCharacteristicRepository.findProductCharacteristicByProductAndCharacteristic(product, characteristic).get(0);
        if(productCharacteristicParm != null)
        {
            delete(productCharacteristicParm.getId());
        }
    }
}
