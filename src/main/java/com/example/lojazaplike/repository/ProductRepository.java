
package com.example.lojazaplike.repository;

import com.example.lojazaplike.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
