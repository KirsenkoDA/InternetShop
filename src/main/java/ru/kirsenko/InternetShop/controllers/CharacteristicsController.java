package ru.kirsenko.InternetShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kirsenko.InternetShop.models.Characteristic;
import ru.kirsenko.InternetShop.models.ProductGroup;
import ru.kirsenko.InternetShop.services.CharacteristicService;
import ru.kirsenko.InternetShop.services.ProductGroupService;

@Controller
@RequiredArgsConstructor//инжект бина модели
@RequestMapping("/characteristics")
public class CharacteristicsController {
    private final CharacteristicService characteristicService;
    @GetMapping
    public String index(Model model)
    {
        model.addAttribute("characteristics", characteristicService.characteristicList());
        return "characteristics/index";
    }
    @GetMapping("/new")
    public String newCharacteristic(Model model)
    {
        model.addAttribute("characteristic", new Characteristic());
        return "characteristics/new.html";
    }

    @PostMapping()
    public String create(@ModelAttribute("characteristic") Characteristic characteristic)
    {
        characteristicService.save(characteristic);
        return "redirect:/characteristics";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id)
    {
        characteristicService.delete(id);
        return "redirect:/characteristics";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model )
    {
        Characteristic characteristic = characteristicService.show(id);
        model.addAttribute("characteristic", characteristic);
        return "characteristics/show";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id)
    {
        model.addAttribute("characteristic", characteristicService.show(id));
        return "characteristics/edit.html";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("characteristic") Characteristic characteristic)
    {
        characteristicService.save(characteristic);
        return "redirect:/characteristics";
    }

}
