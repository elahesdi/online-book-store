package com.TOSAN.onlineBookStore.controller;

import com.TOSAN.onlineBookStore.dto.BookDto;
import com.TOSAN.onlineBookStore.dto.PurchaseDto;
import com.TOSAN.onlineBookStore.dto.ResponseDto;
import com.TOSAN.onlineBookStore.exception.EntityNotFound;
import com.TOSAN.onlineBookStore.exception.OutOfStockException;
import com.TOSAN.onlineBookStore.model.Book;
import com.TOSAN.onlineBookStore.model.Order;
import com.TOSAN.onlineBookStore.model.User;
import com.TOSAN.onlineBookStore.service.BookService;
import com.TOSAN.onlineBookStore.service.InventoryService;
import com.TOSAN.onlineBookStore.service.OrderService;
import com.TOSAN.onlineBookStore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Operation(summary = "Get all books. You can sort results based on title or author")
    @GetMapping("/inventories")
    public List<BookDto> showBooks(@RequestParam(defaultValue = "0", required = false)  Integer pageNo,
                                     @RequestParam(defaultValue = "10", required = false)  Integer pageSize,
                                     @RequestParam(defaultValue = "title", required = false)  String sort){
        return bookService.getAllBooks(pageNo, pageSize, sort).stream().map(BookDto::convertToBookDto)
                .collect(Collectors.toList());

    }
    @GetMapping("/orders")
    public List<Order> getAllOrders(){
        return orderService.findAllOrders();
    }

}
