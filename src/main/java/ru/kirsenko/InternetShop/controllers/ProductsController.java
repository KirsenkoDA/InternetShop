package ru.kirsenko.InternetShop.controllers;

import javax.validation.Valid;
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
    public String index(@RequestParam(name="selectByProductGroup", required = false) String productGroupId, Model model)
    {
        String selectedParam;
        ProductGroup productGroup = null;
        if(productGroupId == null)
        {
            selectedParam = "1";
        }
        else
        {
            productGroup = productGroupService.getProductGroup(Long.parseLong(productGroupId));
            selectedParam = productGroupId;
        }
        model.addAttribute("products", productService.list(productGroup));
        model.addAttribute("groups", productGroupService.groupList());
        model.addAttribute("selectedParam", selectedParam);
        return "product/index";
    }
    @GetMapping("/new")
    public String newProduct(Model model)
    {
        model.addAttribute("product", new Product());
        model.addAttribute("groups", productGroupService.groupList());
        return "product/new.html";
    }

    @PostMapping()
    public String create(Model model, @RequestParam(name="file1", required = false) MultipartFile file1
            , @RequestParam(name="file2", required = false) MultipartFile file2
            , @RequestParam(name="file3", required = false) MultipartFile file3
            , @ModelAttribute("product") @Valid Product product
            , BindingResult bindingResult) throws IOException
    {
        if(bindingResult.hasErrors()) {
            model.addAttribute("groups", productGroupService.groupList());
            return "product/new.html";
        }
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
    public String update(@ModelAttribute("product") @Valid Product product
            , BindingResult bindingResult
            , @PathVariable("id") Long id
            , @RequestParam(name="file1", required = false) MultipartFile file1
            , @RequestParam(name="file2", required = false) MultipartFile file2
            , @RequestParam(name="file3", required = false) MultipartFile file3, Model model) throws IOException
    {
        product.setImages(productService.show(id).getImages());
        if(bindingResult.hasErrors())
        {
            model.addAttribute("groups", productGroupService.groupList());
            return "product/edit.html";
        }
        productService.save(product, file1, file2, file3);
        return "redirect:/products";
    }
}
