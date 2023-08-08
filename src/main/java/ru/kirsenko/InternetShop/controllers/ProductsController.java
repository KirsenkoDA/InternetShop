package ru.kirsenko.InternetShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kirsenko.InternetShop.models.Product;
import ru.kirsenko.InternetShop.services.ProductGroupService;
import ru.kirsenko.InternetShop.services.ProductService;

@Controller
@RequiredArgsConstructor//инжект бина модели
@RequestMapping("/products")
public class ProductsController {
    private final ProductService productService;//инжект бина модели
    private final ProductGroupService productGroupService;

    @GetMapping
    public String index(@RequestParam(name="selectByType", required = false) String type, Model model)
    {
        String selectedParam;
        model.addAttribute("products", productService.list(type));
        model.addAttribute("groups", productGroupService.groupList());
        if(type == null)
        {
            selectedParam = "1";
        }
        else
        {
            selectedParam = type;
        }
        model.addAttribute("selectedParam", selectedParam);
        return "index";
    }
    @GetMapping("/new")
    public String newProduct(Model model)
    {
        model.addAttribute("product", new Product());
        return "new.html";
    }

    @PostMapping()
    public String create(@ModelAttribute("product") Product product)
    {
        productService.save(product);
        return "redirect:/products";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model )
    {
        model.addAttribute("product", productService.show(id));
        return "show.html";
    }
    @DeleteMapping ("/{id}")
    public String delete(@PathVariable Long id)
    {
        productService.delete(id);
        return "redirect:/products";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id)
    {
        model.addAttribute("product", productService.show(id));
        return "edit.html";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("product") Product product, @PathVariable("id") Long id)
    {
        productService.save(product);
        return "redirect:/products";
    }
}
