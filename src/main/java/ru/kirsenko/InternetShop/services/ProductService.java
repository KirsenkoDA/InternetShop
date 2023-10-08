package ru.kirsenko.InternetShop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kirsenko.InternetShop.models.Image;
import ru.kirsenko.InternetShop.models.Product;
import ru.kirsenko.InternetShop.models.ProductGroup;
import ru.kirsenko.InternetShop.repositories.ProductRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public List<Product> list(ProductGroup productGroup) {
        if(productGroup != null)
        {
            return productRepository.findByProductGroup(productGroup);
        }
        return productRepository.findAll();
    }
    public Product show(long id)
    {
        return productRepository.findById(id).orElse(null);
    }
    public void save(Product product, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        Image image1;
        Image image2;
        Image image3;
        if(product.getId() != null)
        {
            List<Image> images = show(product.getId()).getImages();
            product.setImages(images);
        }
        if(file1.getSize() != 0)
        {
            image1 = toImageEntity(file1);
            image1.setPreviewImage(true);
            if(product.getImages().size() > 0)
            {
                image1.setId(product.getImages().get(0).getId());
                product.updateImageFromProduct(image1, 0);
            }
            else
            {
                product.addImageToProduct(image1);
            }
        }
        if(file2.getSize() != 0)
        {
            image2 = toImageEntity(file2);
            if(product.getImages().size() > 1)
            {
                image2.setId(product.getImages().get(1).getId());
                product.updateImageFromProduct(image2, 1);
            }
            else
            {
                product.addImageToProduct(image2);
            }
        }
        if(file3.getSize() != 0)
        {
            image3 = toImageEntity(file3);
            if(product.getImages().size() > 2)
            {
                image3.setId(product.getImages().get(2).getId());
                product.updateImageFromProduct(image3, 2);
            }
            else
            {
                product.addImageToProduct(image3);
            }
        }
        log.info("Saving new Product. Name: {}; Type: {}", product.getName(), product.getProductGroup().getName());
        Product productFromDb = productRepository.save(product);
        productFromDb.setPreviewImageId(productFromDb.getImages().get(0).getId());
        productRepository.save(product);
    }
    private Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setContentType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }
    public void delete(long id)
    {
        log.info("delete{}", id);
        productRepository.deleteById(id);
    }
    public Page<Product> findPaginated(int pageNo, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return productRepository.findAll(pageable);
    }
}
