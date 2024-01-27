package com.TOSAN.onlineBookStore.controller;

import com.TOSAN.onlineBookStore.dto.BookDto;
import com.TOSAN.onlineBookStore.dto.ResponseDto;
import com.TOSAN.onlineBookStore.exception.BookNotFoundException;
import com.TOSAN.onlineBookStore.exception.BookTitleNullException;
import com.TOSAN.onlineBookStore.exception.UserNotFoundException;
import com.TOSAN.onlineBookStore.model.Book;
import com.TOSAN.onlineBookStore.model.Inventory;
import com.TOSAN.onlineBookStore.service.BookService;
import com.TOSAN.onlineBookStore.service.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    private final InventoryService inventoryService;

    public BookController(BookService bookService, InventoryService inventoryService) {
        this.bookService = bookService;
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> addBook(@RequestBody BookDto bookDto){
        try {
            Book book = bookService.addBook(bookDto);
            inventoryService.addInventory(new Inventory(book, 3));
            return ResponseEntity.ok().body(new ResponseDto("Book has been Added Successfully"));
        }catch (BookTitleNullException e){
            return ResponseEntity.badRequest().body(new ResponseDto(e.getMessage()));
        }
    }

    @GetMapping
    public List<BookDto> getAllUsers(@RequestParam(defaultValue = "0", required = false)  Integer pageNo,
                                  @RequestParam(defaultValue = "10", required = false)  Integer pageSize,
                                  @RequestParam(defaultValue = "title", required = false)  String sort){
         return bookService.getAllBooks(pageNo, pageSize, sort).stream().map(BookDto::convertToBookDto)
                .collect(Collectors.toList());

    }

    @PutMapping
    public ResponseEntity<ResponseDto> updateBook(@RequestBody BookDto bookDto){
        try{
            bookService.updateBook(bookDto);
            return ResponseEntity.ok().body(new ResponseDto("The book has been updated successfully"));
        }catch (BookNotFoundException e) {
            return ResponseEntity.badRequest().body(new ResponseDto(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable Long id)  {
        try {
            bookService.deleteUser(id);
            return ResponseEntity.ok().body(new ResponseDto("The book has been deleted successfully"));
        } catch (BookNotFoundException e) {
            return ResponseEntity.badRequest().body(new ResponseDto(e.getMessage()));
        }
    }


}
