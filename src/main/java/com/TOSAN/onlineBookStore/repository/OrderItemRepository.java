package com.TOSAN.onlineBookStore.repository;

import com.TOSAN.onlineBookStore.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
