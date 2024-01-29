package com.TOSAN.onlineBookStore.service;

import com.TOSAN.onlineBookStore.dto.BookDto;
import com.TOSAN.onlineBookStore.exception.BookNotFoundException;
import com.TOSAN.onlineBookStore.exception.BookTitleNullException;
import com.TOSAN.onlineBookStore.model.Book;
import com.TOSAN.onlineBookStore.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImp bookServiceImp;

    private Book book;

    @BeforeEach
    public void setup() {
        book = new Book("Title", "Author");
    }


    @Test
    public void createBook_successfully() throws BookTitleNullException {
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        Book savedBook = bookServiceImp.addBook(BookDto.convertToBookDto(book));
        assertNotNull(savedBook);
        assertEquals("Title", savedBook.getTitle());
        assertEquals("Author", savedBook.getAuthor());
    }
}
