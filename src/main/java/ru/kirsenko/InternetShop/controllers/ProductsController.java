package ru.kirsenko.InternetShop.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kirsenko.InternetShop.models.Product;
import ru.kirsenko.InternetShop.models.ProductGroup;
import ru.kirsenko.InternetShop.services.ProductGroupService;
import ru.kirsenko.InternetShop.services.ProductService;

import java.io.IOException;

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
        model.addAttribute("groups", productGroupService.groupList());
        return "product/new.html";
    }

    @PostMapping()
    public String create(@RequestParam(name="file1", required = false) MultipartFile file1
            , @RequestParam(name="file2", required = false) MultipartFile file2
            , @RequestParam(name="file3", required = false) MultipartFile file3
            , @ModelAttribute("product") @Valid Product product
            , BindingResult bindingResult) throws IOException
    {
        if(bindingResult.hasErrors())
            return "product/new.html";
        productService.save(product, file1, file2, file3);
        return "redirect:/products";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model )
    {
        Product product = productService.show(id);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());
        return "product/show.html";
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
        model.addAttribute("groups", productGroupService.groupList());
        return "product/edit.html";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @PathVariable("id") Long id, @RequestParam("file1") MultipartFile file1
            , @RequestParam("file2") MultipartFile file2
            , @RequestParam("file3") MultipartFile file3) throws IOException
    {
        if(bindingResult.hasErrors())
        {
            return "product/edit.html";
        }
        productService.save(product, file1, file2, file3);
        return "redirect:/products";
    }
}
