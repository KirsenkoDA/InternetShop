package ru.kirsenko.InternetShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kirsenko.InternetShop.services.ProductGroupService;

@Controller
@RequiredArgsConstructor//инжект бина модели
@RequestMapping("/characteristics")
public class CharacteristicsController {
    private final ProductGroupService productGroupService;
    @GetMapping
    public String index(Model model)
    {
        model.addAttribute("productGroups", productGroupService.groupList());
        return "productGroup/index";
    }
}
