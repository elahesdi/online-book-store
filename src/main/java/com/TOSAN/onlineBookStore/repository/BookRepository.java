package com.TOSAN.onlineBookStore.repository;

import com.TOSAN.onlineBookStore.model.Book;
import com.TOSAN.onlineBookStore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);
    Optional<Book> findById(Long id);
    Optional<Book> findBookByTitle(String title);
}
