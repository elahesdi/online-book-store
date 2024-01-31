package com.TOSAN.onlineBookStore.service;

import com.TOSAN.onlineBookStore.exception.EntityNotFound;
import com.TOSAN.onlineBookStore.model.Order;

import java.util.List;

public interface OrderService {

    Order addOrder(Order order);
    Order findById(Long id) throws EntityNotFound;
    public List<Order> findAllOrders();

}
