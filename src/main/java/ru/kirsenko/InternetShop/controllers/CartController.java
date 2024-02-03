package ru.kirsenko.InternetShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kirsenko.InternetShop.models.Cart;
import ru.kirsenko.InternetShop.models.Characteristic;
import ru.kirsenko.InternetShop.models.Product;
import ru.kirsenko.InternetShop.models.User;
import ru.kirsenko.InternetShop.services.CartService;
import ru.kirsenko.InternetShop.services.ProductService;
import ru.kirsenko.InternetShop.services.UserService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;
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
}
