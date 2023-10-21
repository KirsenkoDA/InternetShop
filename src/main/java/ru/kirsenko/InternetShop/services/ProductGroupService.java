package ru.kirsenko.InternetShop.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kirsenko.InternetShop.models.Characteristic;
import ru.kirsenko.InternetShop.models.Product;
import ru.kirsenko.InternetShop.models.ProductGroup;
import ru.kirsenko.InternetShop.repositories.ProductGroupRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
public class ProductGroupService {
    private final ProductGroupRepository productGroupRepository;
    private final CharacteristicService characteristicService;
    public List<ProductGroup> groupList() {
        return productGroupRepository.findAll();
    }
    public ProductGroup getProductGroup(Long productGroupId) {
        return productGroupRepository.findById(productGroupId).get();
    }
    public ProductGroup show(long id)
    {
        return productGroupRepository.findById(id).orElse(null);
    }
    public void save(ProductGroup productGroup){
        productGroupRepository.save(productGroup);
    }
    public void delete(long id)
    {
        log.info("delete{}", id);
        productGroupRepository.deleteById(id);
    }
    public ProductGroup findById(long id)
    {
        return productGroupRepository.findById(id).orElse(null);
    }
    public void deleteCharacteristic(long characteristicId, ProductGroup productGroup)
    {
//        List<String> myList = new ArrayList<>();
//
//        // добавляем элементы в список, используя метод the add()
//        myList.add("apple");
//        myList.add("banana");
//        myList.add("cherry");
//        String a  = myList.get(1);
//        myList.forEach(characteristicList1 -> {
//            ;
//        });
//        {
//            a = characteristicList1;
//        }
//        System.out.println(myList);
//        int i = 0;
//        productGroup.getCharacteristics().forEach(characteristic -> {
//            if(characteristicId == characteristic.getId())
//            {
//                productGroup.getCharacteristics().remove(i);
//            }
//            i++;
//        });
        productGroup.getCharacteristics().removeIf(n ->(n.getId() == characteristicId));
//        for(Characteristic characteristicList1: productGroup.getCharacteristics());
//        {
//            if(characteristicId == productGroup.getCharacteristics().)
//            {
//                productGroup.getCharacteristics().remove()
//            }
//        }
        productGroupRepository.save(productGroup);
    }
    public List<Characteristic> characteristicList() {
        return characteristicService.characteristicList();
    }
}
