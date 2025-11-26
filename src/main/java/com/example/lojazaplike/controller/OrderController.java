package com.example.lojazaplike.controller;

import com.example.lojazaplike.model.*;
import com.example.lojazaplike.repository.*;
import com.example.lojazaplike.dto.CheckoutRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    private CartItemRepository cartRepo;

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private OrderItemRepository orderItemRepo;

    @Autowired
    private ProductRepository productRepo;

    @PostMapping("/checkout")
    @Transactional
    public ResponseEntity<OrderEntity> checkout(@RequestBody CheckoutRequest req) {

        System.out.println(">>> RECEBIDO: " + req);

        if (req.getSessionId() == null || req.getSessionId().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<CartItem> cart = cartRepo.findBySessionId(req.getSessionId());
        if (cart == null || cart.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // montar endereço
        String fullAddress =
                req.getStreet() + ", " +
                req.getNumber() +
                (req.getComplement() != null ? " - " + req.getComplement() : "") +
                ", " + req.getRegion() +
                ", CEP: " + req.getCep() +
                (req.getReference() != null ? " (Ref: " + req.getReference() + ")" : "");

        OrderEntity order = new OrderEntity();
        order.setName(req.getName());
        order.setPhone(req.getPhone());
        order.setAddress(fullAddress);
        order.setPaymentMethod(req.getPaymentMethod());
        order.setStatus("PENDING");

        // itens
        List<OrderItem> items = cart.stream().map(ci -> {
            OrderItem oi = new OrderItem();
            oi.setProduct(ci.getProduct());
            oi.setQuantity(ci.getQuantity());
            oi.setPrice(ci.getPrice());
            oi.setOrder(order);
            return oi;
        }).collect(Collectors.toList());

        order.setItems(items);

        double itemsTotal = items.stream()
                .mapToDouble(i -> i.getPrice() * i.getQuantity())
                .sum();

        order.setTotal(itemsTotal);

        OrderEntity saved = orderRepo.save(order);

        cartRepo.deleteBySessionId(req.getSessionId());

        return ResponseEntity.ok(saved);
    }
}