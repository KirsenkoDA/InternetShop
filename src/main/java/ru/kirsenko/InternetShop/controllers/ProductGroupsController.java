package ru.kirsenko.InternetShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kirsenko.InternetShop.models.Characteristic;
import ru.kirsenko.InternetShop.models.Product;
import ru.kirsenko.InternetShop.models.ProductGroup;
import ru.kirsenko.InternetShop.models.Role;
import ru.kirsenko.InternetShop.services.ProductGroupService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    @GetMapping("/new")
    public String newGroup(Model model)
    {
        model.addAttribute("productGroup", new ProductGroup());
        return "productGroup/new.html";
    }

    @PostMapping()
    public String create(@ModelAttribute("productGroup") ProductGroup productGroup)
    {
        productGroupService.save(productGroup);
        return "redirect:/productGroups";
    }
    @DeleteMapping ("/{id}")
    public String delete(@PathVariable Long id)
    {
        productGroupService.delete(id);
        return "redirect:/productGroups";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id)
    {
        model.addAttribute("productGroup", productGroupService.show(id));
        return "productGroup/edit.html";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("productGroup") ProductGroup productGroup, @PathVariable("id") Long id)
    {
        productGroup.setCharacteristics(productGroupService.show(id).getCharacteristics());
        productGroupService.save(productGroup);
        return "redirect:/productGroups";
    }
    @DeleteMapping ("/deleteCharacteristic/{id}")
    public String deleteCharacteristic(@PathVariable Long id, ProductGroup productGroup)
    {
        productGroupService.deleteCharacteristic(id, productGroup);
        String url = "redirect:/productGroups/"+id+"/edit";
        return "redirect:/productGroups";
    }
    @GetMapping("/editCharacteristics/{id}")
    public String editCharacteristics(@PathVariable Long id, Model model)
    {
        model.addAttribute("productGroup", productGroupService.show(id));
        model.addAttribute("characteristc", productGroupService.characteristicList());
        return "productGroup/addCharacteristics.html";
    }
    @PostMapping("/updateCharacteristics/{id}")
    public String updateCharacteristics(@ModelAttribute("productGroup") ProductGroup productGroup, @PathVariable("id") Long id)
    {
        productGroupService.save(productGroup);
        return "redirect:/productGroups";
    }
}
