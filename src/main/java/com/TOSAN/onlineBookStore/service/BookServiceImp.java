package com.TOSAN.onlineBookStore.service;

import com.TOSAN.onlineBookStore.dto.BookDto;
import com.TOSAN.onlineBookStore.exception.BookNotFoundException;
import com.TOSAN.onlineBookStore.exception.BookTitleNullException;
import com.TOSAN.onlineBookStore.model.Book;
import com.TOSAN.onlineBookStore.repository.BookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;

    }
    @Override
    public Book addBook(BookDto bookDto) throws BookTitleNullException {
        Optional.ofNullable(bookDto.getTitle()).orElseThrow(BookTitleNullException::new);
        Book book = new Book(bookDto);
        return bookRepository.save(book);
    }
    @Override
    public List<Book> getAllBooks(int pageNo, int pageSize, String sort){
        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(sort).ascending());
        return bookRepository.findAll(pageRequest).getContent();
    }
    @Override
    public Book getBook(Long id) throws BookNotFoundException {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id.toString()));
    }

    @Override
    public Book updateBook(BookDto bookDto) throws BookNotFoundException {
        Book book = bookRepository.findById(bookDto.getId()).orElseThrow(() -> new BookNotFoundException(bookDto.getId().toString()));
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        bookRepository.save(book);
        return book;
    }
    @Override
    public void deleteUser(Long id) throws BookNotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id.toString()));
        bookRepository.delete(book);
    }

    @Override
    public Book findByTitle(String title) throws BookNotFoundException {
        return bookRepository.findByTitle(title).orElseThrow(() -> new BookNotFoundException(title));
    }

    public List<Book> findAll() {
       return bookRepository.findAll();
    }
}
