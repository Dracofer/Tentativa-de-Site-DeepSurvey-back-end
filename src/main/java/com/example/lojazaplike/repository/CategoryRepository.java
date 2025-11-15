package com.example.lojazaplike.repository;

import com.example.lojazaplike.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {}