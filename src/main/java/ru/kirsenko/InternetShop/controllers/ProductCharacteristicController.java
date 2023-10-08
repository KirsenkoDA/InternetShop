package ru.kirsenko.InternetShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kirsenko.InternetShop.models.Product;
import ru.kirsenko.InternetShop.models.ProductCharacteristic;
import ru.kirsenko.InternetShop.models.ProductGroup;
import ru.kirsenko.InternetShop.services.ProductCharacteristicService;
import ru.kirsenko.InternetShop.services.ProductService;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/productCharacteristics")
public class ProductCharacteristicController {
    private final ProductCharacteristicService productCharacteristicService;
    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model )
    {
        model.addAttribute("productCharacteristic", productCharacteristicService.show(id));
        return "productCharacteristic/show";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id)
    {
        model.addAttribute("productCharacteristic", productCharacteristicService.show(id));
        return "productCharacteristic/edit";
    }
    @PatchMapping("/{id}")
    public String update(@RequestParam(name="productCharacteristicId") Long productCharacteristicId, @RequestParam(name="productCharacteristicValue") String productCharacteristicValue, Model model)
    {
        ProductCharacteristic productCharacteristic = new ProductCharacteristic();
        productCharacteristic.setProduct(productCharacteristicService.show(productCharacteristicId).getProduct());
        productCharacteristic.setCharacteristic(productCharacteristicService.show(productCharacteristicId).getCharacteristic());
        productCharacteristic.setProductCharacteristicValue(productCharacteristicValue);
        productCharacteristic.setId(productCharacteristicId);
        productCharacteristicService.save(productCharacteristic);

        return "redirect:/products/" + Long.toString(productCharacteristic.getProduct().getId());
    }
}
