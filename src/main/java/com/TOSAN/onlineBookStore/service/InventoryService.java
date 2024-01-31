package com.TOSAN.onlineBookStore.service;

import com.TOSAN.onlineBookStore.exception.EntityNotFound;
import com.TOSAN.onlineBookStore.exception.OutOfStockException;
import com.TOSAN.onlineBookStore.model.Book;
import com.TOSAN.onlineBookStore.model.Inventory;
import org.springframework.stereotype.Service;


public interface InventoryService {
    Inventory addInventory(Inventory inventory);
    Inventory updateInventory(Inventory inventory);
    void purchase(Book book, int quantity) throws EntityNotFound, OutOfStockException;
}
