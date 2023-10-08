package ru.kirsenko.InternetShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kirsenko.InternetShop.models.ProductCharacteristic;
import ru.kirsenko.InternetShop.models.ProductGroup;
import ru.kirsenko.InternetShop.services.ProductCharacteristicService;
import ru.kirsenko.InternetShop.services.ProductService;

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
}
