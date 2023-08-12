package ru.kirsenko.InternetShop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kirsenko.InternetShop.models.Product;
import ru.kirsenko.InternetShop.models.ProductGroup;
import ru.kirsenko.InternetShop.repositories.ProductGroupRepository;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class ProductGroupService {
    private final ProductGroupRepository productGroupRepository;
    public List<ProductGroup> groupList() {
        return productGroupRepository.findAll();
    }
    public ProductGroup getProductGroup(Long productGroupId) {
        return productGroupRepository.findById(productGroupId).get();
    }
}
