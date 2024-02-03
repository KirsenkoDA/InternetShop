package ru.kirsenko.InternetShop.services;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kirsenko.InternetShop.models.Cart;
import ru.kirsenko.InternetShop.models.Characteristic;
import ru.kirsenko.InternetShop.models.Product;
import ru.kirsenko.InternetShop.models.User;
import ru.kirsenko.InternetShop.repositories.CartRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    public Cart findToCountProducts(User user, Product product)
    {
        return cartRepository.findByUserAndProduct(user, product);
    }
    public void addToCart(Cart cart){
        cartRepository.save(cart);
    }
    public List<Cart> findByUser (User user)
    {
        return cartRepository.findAllByUser(user);
    }
    public void delete(Long id){cartRepository.deleteById(id);}
}
