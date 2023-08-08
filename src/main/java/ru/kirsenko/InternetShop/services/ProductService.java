package ru.kirsenko.InternetShop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kirsenko.InternetShop.models.Product;
import ru.kirsenko.InternetShop.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> list(String type) {
        if(type != null)
        {
            return productRepository.findByType(Integer.parseInt(type));
        }
        return productRepository.findAll();
    }
    public Product show(long id)
    {
        return productRepository.findById(id).orElse(null);
    }
    public void save(Product product)
    {
        log.info("Saving new {}", product);
        productRepository.save(product);
    }
    public void delete(long id)
    {
        productRepository.deleteById(id);
    }
}

