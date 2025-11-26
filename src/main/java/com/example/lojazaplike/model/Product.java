package com.example.lojazaplike.model;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length = 1000)
    private String description;

    private Double price;

    private Integer stock;

    private String imageUrl;
    
    @OneToMany(mappedBy = "product")
    @JsonIgnore   // Não é necessário enviar itens relacionados ao produto
    private List<OrderItem> items;
    
    private Boolean onSale = false;
    private Double salePrice;

    @ManyToOne
    private Category category;
}