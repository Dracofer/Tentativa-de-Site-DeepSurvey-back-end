
package com.example.lojazaplike.repository;

import com.example.lojazaplike.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByCategoryId(Long id);
	List<Product> findByNameContainingIgnoreCase(String name);
	List<Product> findByOnSaleTrue();
}
