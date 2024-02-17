package ru.kirsenko.InternetShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kirsenko.InternetShop.models.*;
import ru.kirsenko.InternetShop.services.SalesLineService;
import ru.kirsenko.InternetShop.services.SalesTableService;
import ru.kirsenko.InternetShop.services.StatusService;

import java.util.List;

@Controller
@RequiredArgsConstructor//инжект бина модели
@RequestMapping("/salesTables")
public class SalesTablesController {
    private final SalesTableService salesTableService;
    private final SalesLineService salesLineService;
    private final StatusService statusService;
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model, @AuthenticationPrincipal User user) {
        int pageSize = 3;
        Page<SalesTable> page;
        if (user.getRoles().contains("ROLE_ADMIN"))
        {
            page = salesTableService.findPaginated(pageNo - 1, pageSize);
        }
        else
        {
            page = salesTableService.findPaginatedByUser(pageNo - 1, pageSize, user);
        }
        List<SalesTable> salesTables = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("salesTables", salesTables);
        return "salesTables/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model)
    {
        List<SalesLine> salesLines = salesLineService.findBySalesTableList(salesTableService.show(id));
        SalesTable salesTable = salesTableService.show(id);
        model.addAttribute("salesTable", salesTable);
        model.addAttribute("salesLines", salesLines);
        return "salesTables/show";
    }
    @PostMapping("/accept/{id}")
    public String accept(@PathVariable Long id, @RequestParam(name="address", required = false) String address)
    {
        SalesTable salesTable = salesTableService.show(id);
        salesTable.setAddress(address);
        salesTable.setStatus(statusService.show(1));
        salesTableService.save(salesTable);
        return "salesTables/page/1";
    }
}
