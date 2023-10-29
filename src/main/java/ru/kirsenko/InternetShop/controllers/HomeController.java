package ru.kirsenko.InternetShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kirsenko.InternetShop.models.Product;
import ru.kirsenko.InternetShop.models.ProductGroup;
import ru.kirsenko.InternetShop.services.ProductGroupService;
import ru.kirsenko.InternetShop.services.ProductService;

import java.util.List;

@Controller
@RequiredArgsConstructor//инжект бина модели
@RequestMapping("/home")
public class HomeController {
    private final ProductService productService;
    private final ProductGroupService productGroupService;
    @GetMapping("/personalAccount")
    public String personalAccount()
    {
        return "userMainPage/personalAccount.html";
    }
    @GetMapping("/")
    public String index()
    {
        return "redirect:/home/page/1";
    }
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model, @RequestParam(name="selectByProductGroup", required = false) String productGroupId)
    {
        String selectedParam;
        ProductGroup productGroup = null;

        if(productGroupId == null) {
            productGroupId = "1";
            selectedParam = productGroupId;
        }
        else
        {
            productGroup = productGroupService.getProductGroup(Long.parseLong(productGroupId));
            selectedParam = productGroupId;
        }
        int pageSize = 3;

        Page<Product> page = productService.findPaginatedByProductGroup(pageNo - 1, pageSize, productGroup);
        List< Product > listProducts = page.getContent();

        model.addAttribute("groups", productGroupService.groupList());
        model.addAttribute("selectedGroup", productGroupService.findById(Long.parseLong(selectedParam)));
        model.addAttribute("selectedParam", selectedParam);
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listProducts", listProducts);
        return "userMainPage/home.html";
    }
}
