package com.TOSAN.onlineBookStore.service;

import com.TOSAN.onlineBookStore.exception.EntityNotFound;
import com.TOSAN.onlineBookStore.model.Book;
import com.TOSAN.onlineBookStore.model.Order;
import com.TOSAN.onlineBookStore.model.User;
import com.TOSAN.onlineBookStore.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public interface OrderService {

    Order addOrder(Order order);
    Order findById(Long id) throws EntityNotFound;

}
