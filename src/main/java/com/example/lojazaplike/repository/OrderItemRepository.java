
package com.example.lojazaplike.repository;

import com.example.lojazaplike.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
