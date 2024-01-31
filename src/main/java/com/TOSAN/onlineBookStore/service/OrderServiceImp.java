package com.TOSAN.onlineBookStore.service;

import com.TOSAN.onlineBookStore.exception.EntityNotFound;
import com.TOSAN.onlineBookStore.model.Order;
import com.TOSAN.onlineBookStore.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImp implements OrderService{

    private final OrderRepository orderRepository;

    public OrderServiceImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order addOrder(Order order){
        return orderRepository.save(order);
    }

    @Override
    public Order findById(Long id) throws EntityNotFound {
        return orderRepository.findById(id).orElseThrow(EntityNotFound::new);
    }

    public List<Order> findAllOrders() {
        return orderRepository.findAll();
    }
}
