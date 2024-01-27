package com.TOSAN.onlineBookStore.repository;

import com.TOSAN.onlineBookStore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
