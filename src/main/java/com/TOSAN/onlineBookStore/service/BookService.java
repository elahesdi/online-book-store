package com.TOSAN.onlineBookStore.service;

import com.TOSAN.onlineBookStore.dto.BookDto;
import com.TOSAN.onlineBookStore.exception.BookTitleNullException;
import com.TOSAN.onlineBookStore.model.Book;

import java.util.List;


public interface BookService {

    Book addBook(BookDto bootDto) throws BookTitleNullException;
    List<Book> getAllBooks(int pageNo, int pageSize, String sort);

}
