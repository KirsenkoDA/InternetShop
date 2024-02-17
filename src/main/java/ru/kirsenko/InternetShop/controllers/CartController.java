package ru.kirsenko.InternetShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kirsenko.InternetShop.models.*;
import ru.kirsenko.InternetShop.services.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;
    private final StatusService statusService;
    private final SalesTableService salesTableService;
    private final SalesLineService salesLineService;
    @GetMapping()
    public String show(@AuthenticationPrincipal User user, Model model)
    {
        List<Cart> carts = cartService.findByUser(user);
        model.addAttribute("carts", carts);
        return "cart/show";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id)
    {
        cartService.delete(id);
        return "redirect:/carts";
    }
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable("id") Long productId, @AuthenticationPrincipal User user)
    {
        Product product = productService.show(productId);
        Cart existingCart = cartService.findToCountProducts(user, product);
        if(cartService.findToCountProducts(user, product)!= null)
        {
            existingCart.setQuantity(existingCart.getQuantity() + 1);
            existingCart.setAmount(existingCart.getPrice() * existingCart.getQuantity());
            cartService.addToCart(existingCart);
        }
        else
        {
            Cart newCart = new Cart();
            newCart.setProduct(product);
            newCart.setUser(user);
            newCart.setPrice(product.getPrice());
            newCart.setQuantity(1);
            newCart.setAmount(newCart.getPrice());
            cartService.addToCart(newCart);
        }
        return "redirect:/carts";
    }
    @PostMapping("/makeOrder")
    public String makeOrder(@AuthenticationPrincipal User user)
    {
        //Получаем списко товаров из карзины
        List<Cart> carts = cartService.findByUser(user);
        //Создаём новый заказ
        SalesTable salesTable = new SalesTable();
        //Новый заказ "не оформлен"
        salesTable.setStatus(statusService.show(3));
        salesTable.setUser(user);
        salesTable.setDateCreated(LocalDateTime.now());
        //Сохранение нового заказа
        salesTableService.save(salesTable);
        //salesTable.setId(salesTableService.findByStatusAndUser(statusService.show(3), user).getId());
        //Создание строк заказа
        for(Cart cart: carts)
        {
            SalesLine salesLine = new SalesLine();
            salesLine.setSalesTable(salesTable);
            salesLine.setProduct(cart.getProduct());
            salesLine.setQuantity(cart.getQuantity());
            salesLine.setPrice(cart.getPrice());
            salesLineService.save(salesLine);
            cartService.delete(cart.getId());
        }
        return "redirect:/salesTables/" + salesTable.getId();
    }
}
