
package com.example.lojazaplike.controller;

import com.example.lojazaplike.model.*;
import com.example.lojazaplike.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired private CartItemRepository cartRepo;
    @Autowired private OrderRepository orderRepo;
    @Autowired private OrderItemRepository orderItemRepo;
    @Autowired private UserRepository userRepo;

    @PostMapping("/checkout")
    public OrderEntity checkout(Authentication auth) {
        User u = userRepo.findByUsername(auth.getName()).orElseThrow();
        List<CartItem> cart = cartRepo.findByUser(u);
        if (cart.isEmpty()) throw new RuntimeException("cart_empty");
        OrderEntity order = new OrderEntity();
        order.setUser(u);
        order.setStatus("PENDING");
        List<OrderItem> items = cart.stream().map(ci -> {
            OrderItem oi = new OrderItem();
            oi.setProduct(ci.getProduct());
            oi.setQuantity(ci.getQuantity());
            oi.setPrice(ci.getProduct().getPrice());
            return oi;
        }).collect(Collectors.toList());
        order.setItems(items);
        double total = items.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();
        order.setTotal(total);
        orderRepo.save(order);
        cartRepo.deleteByUser(u);
        return order;
    }

    @GetMapping
    public List<OrderEntity> list(Authentication auth) {
        // simple: admins can see all; users see their own
        User u = userRepo.findByUsername(auth.getName()).orElseThrow();
        if (u.getRoles().contains(Role.ROLE_ADMIN)) {
            return orderRepo.findAll();
        } else {
            return orderRepo.findAll().stream().filter(o -> o.getUser().getId().equals(u.getId())).collect(Collectors.toList());
        }
    }
}
