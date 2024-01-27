package com.TOSAN.onlineBookStore.service;

import com.TOSAN.onlineBookStore.dto.BookDto;
import com.TOSAN.onlineBookStore.exception.BookNotFoundException;
import com.TOSAN.onlineBookStore.exception.BookTitleNullException;
import com.TOSAN.onlineBookStore.model.Book;

import java.util.List;


public interface BookService {

    Book addBook(BookDto bootDto) throws BookTitleNullException;
    List<Book> getAllBooks(int pageNo, int pageSize, String sort);
    Book updateBook(BookDto bookDto) throws BookNotFoundException;
    void deleteUser(Long id) throws BookNotFoundException;
    Book getBook(Long id) throws BookNotFoundException;

}
