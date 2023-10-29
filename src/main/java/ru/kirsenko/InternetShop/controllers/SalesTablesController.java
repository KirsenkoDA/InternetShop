package ru.kirsenko.InternetShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kirsenko.InternetShop.models.Product;
import ru.kirsenko.InternetShop.models.SalesTable;
import ru.kirsenko.InternetShop.services.SalesTableService;

import java.util.List;

@Controller
@RequiredArgsConstructor//инжект бина модели
@RequestMapping("/salesTables")
public class SalesTablesController {
    private final SalesTableService salesTableService;
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 3;

        Page<SalesTable> page = salesTableService.findPaginated(pageNo - 1, pageSize);
        List<SalesTable> salesTables = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("salesTables", salesTables);
        return "salesTables/index";
    }
}
