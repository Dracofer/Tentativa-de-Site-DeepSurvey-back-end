
package com.example.lojazaplike.model;

import javax.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(length=1000)
    private String description;

    private Double price;

    private Integer stock;

    private String imageUrl;   // 👈 ADICIONE ISTO AGORA

    @ManyToOne
    private Category category;
}