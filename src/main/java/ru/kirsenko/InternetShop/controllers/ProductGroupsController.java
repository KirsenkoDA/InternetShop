package ru.kirsenko.InternetShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kirsenko.InternetShop.models.Product;
import ru.kirsenko.InternetShop.models.ProductGroup;
import ru.kirsenko.InternetShop.services.ProductGroupService;

@Controller
@RequiredArgsConstructor//инжект бина модели
@RequestMapping("/productGroups")
public class ProductGroupsController {
    private final ProductGroupService productGroupService;
    @GetMapping
    public String index(Model model)
    {
        model.addAttribute("productGroups", productGroupService.groupList());
        return "productGroup/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model )
    {
        ProductGroup productGroup = productGroupService.show(id);
        model.addAttribute("productGroup", productGroup);
        return "productGroup/show";
    }
}
