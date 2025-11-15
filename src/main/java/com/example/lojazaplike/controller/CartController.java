
package com.example.lojazaplike.controller;

import com.example.lojazaplike.model.*;
import com.example.lojazaplike.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired private CartItemRepository cartRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private ProductRepository productRepo;

    @GetMapping
    public List<CartItem> getCart(Authentication auth) {
        User u = userRepo.findByUsername(auth.getName()).orElseThrow();
        return cartRepo.findByUser(u);
    }

    @PostMapping("/add")
    public CartItem addToCart(Authentication auth, @RequestBody CartItem item) {
        User u = userRepo.findByUsername(auth.getName()).orElseThrow();
        Product p = productRepo.findById(item.getProduct().getId()).orElseThrow();
        CartItem c = new CartItem();
        c.setUser(u);
        c.setProduct(p);
        c.setQuantity(item.getQuantity() == null ? 1 : item.getQuantity());
        return cartRepo.save(c);
    }

    @PostMapping("/clear")
    public String clearCart(Authentication auth) {
        User u = userRepo.findByUsername(auth.getName()).orElseThrow();
        cartRepo.deleteByUser(u);
        return "ok";
    }
}
