package com.TOSAN.onlineBookStore.controller;

import com.TOSAN.onlineBookStore.dto.PurchaseDto;
import com.TOSAN.onlineBookStore.dto.ResponseDto;
import com.TOSAN.onlineBookStore.exception.BookNotFoundException;
import com.TOSAN.onlineBookStore.exception.EntityNotFound;
import com.TOSAN.onlineBookStore.exception.OutOfStockException;
import com.TOSAN.onlineBookStore.exception.UserNotFoundException;
import com.TOSAN.onlineBookStore.model.Book;
import com.TOSAN.onlineBookStore.model.Order;
import com.TOSAN.onlineBookStore.model.User;
import com.TOSAN.onlineBookStore.service.BookService;
import com.TOSAN.onlineBookStore.service.InventoryService;
import com.TOSAN.onlineBookStore.service.OrderService;
import com.TOSAN.onlineBookStore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/purchase")
public class OrderController {
    private final UserService userService;

    private final InventoryService inventoryService;

    private final BookService bookService;

    private final OrderService orderService;

    public OrderController(UserService userService, InventoryService inventoryService, BookService bookService, OrderService orderService) {
        this.userService = userService;
        this.inventoryService = inventoryService;
        this.bookService = bookService;
        this.orderService = orderService;
    }

    @PostMapping("/book")
    public ResponseEntity<ResponseDto> purchase(@RequestBody ArrayList<PurchaseDto> purchaseDtoList){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByUsername(authentication.getName());
            Set<Book> bookSet = new HashSet<>();
            Book book = null;
            for (PurchaseDto purchaseDto : purchaseDtoList){
                 book = bookService.findByTitle(purchaseDto.getBookTitle());
                bookSet.add(book);
                inventoryService.purchase(book, purchaseDto.getQuantity());
            }
            Order order = new Order(user);
            order.setBooks(bookSet);
            orderService.addOrder(order);
            return ResponseEntity.ok().body(new ResponseDto("The purchase was made successfully"));
        }catch (EntityNotFound e){
            return ResponseEntity.badRequest().body(new ResponseDto(e.getMessage()));
        }catch (OutOfStockException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDto(e.getMessage()));
        }
}
}
