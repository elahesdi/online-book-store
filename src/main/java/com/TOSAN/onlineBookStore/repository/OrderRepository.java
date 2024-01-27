package com.TOSAN.onlineBookStore.repository;

import com.TOSAN.onlineBookStore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
