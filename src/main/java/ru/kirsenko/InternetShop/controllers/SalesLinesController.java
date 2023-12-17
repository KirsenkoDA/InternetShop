package ru.kirsenko.InternetShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kirsenko.InternetShop.models.*;
import ru.kirsenko.InternetShop.services.ProductService;
import ru.kirsenko.InternetShop.services.SalesLineService;
import ru.kirsenko.InternetShop.services.SalesTableService;
import ru.kirsenko.InternetShop.services.StatusService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor//инжект бина модели
@RequestMapping("/salesLines")
public class SalesLinesController {
    private final SalesLineService salesLineService;
    private final ProductService productService;
    private final SalesTableService salesTableService;
    private final StatusService statusService;
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 3;

        Page<SalesLine> page = salesLineService.findPaginated(pageNo - 1, pageSize);
        List<SalesLine> salesLines = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("salesLines", salesLines);
        return "salesLine/index";
    }
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable("id") Long id, @AuthenticationPrincipal User user)
    {
        SalesLine salesLine = new SalesLine();
        //Создание новой корзины, если она не создана
        if(salesTableService.findByStatusAndUser(statusService.show(3), user) == null)
        {
            SalesTable salesTable = new SalesTable();
            salesTable.setUser(user);
            salesTable.setStatus(statusService.show(3));
            salesTableService.save(salesTable);
        }
        salesLine.setProduct(productService.show(id));
        salesLine.setPrice(productService.show(id).getPrice());
        salesLine.setQuantity(1);
        salesLineService.save(salesLine);
        return "redirect:/home/page/1";
    }
    @GetMapping("/cart/page/{pageNo}")
    public String cartSalesLines(@PathVariable(value = "pageNo") int pageNo, Model model, @AuthenticationPrincipal User user) {
        int pageSize = 3;
        SalesTable salesTable = salesTableService.findByStatusAndUser(statusService.show(3), user);
        Page<SalesLine> page = salesLineService.findBySalesTable(pageNo - 1, pageSize, salesTable);
        List<SalesLine> cartSalesLines = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("cartSalesLines", cartSalesLines);
        return "userMainPage/cart";
    }
}
