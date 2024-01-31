package com.TOSAN.onlineBookStore.service;

import com.TOSAN.onlineBookStore.exception.EntityNotFound;
import com.TOSAN.onlineBookStore.model.Book;
import com.TOSAN.onlineBookStore.model.Order;
import com.TOSAN.onlineBookStore.model.User;
import com.TOSAN.onlineBookStore.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

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
}
