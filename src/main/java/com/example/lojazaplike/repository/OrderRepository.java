
package com.example.lojazaplike.repository;

import com.example.lojazaplike.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
