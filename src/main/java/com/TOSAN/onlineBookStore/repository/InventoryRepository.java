package com.TOSAN.onlineBookStore.repository;

import com.TOSAN.onlineBookStore.model.Book;
import com.TOSAN.onlineBookStore.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findAllByBook(Book book);

}
