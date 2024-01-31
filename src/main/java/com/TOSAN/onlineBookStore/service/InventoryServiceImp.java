package com.TOSAN.onlineBookStore.service;

import com.TOSAN.onlineBookStore.exception.EntityNotFound;
import com.TOSAN.onlineBookStore.exception.OutOfStockException;
import com.TOSAN.onlineBookStore.model.Book;
import com.TOSAN.onlineBookStore.model.Inventory;
import com.TOSAN.onlineBookStore.repository.InventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImp implements InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryServiceImp(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory addInventory(Inventory inventory){
        return inventoryRepository.save(inventory);
    }
    @Override
    public Inventory updateInventory(Inventory inventory){
        return inventoryRepository.save(inventory);
    }

    private Boolean inStock(Book book, int quantity) throws EntityNotFound {
       Inventory inventory = inventoryRepository.findAllByBook(book).orElseThrow(EntityNotFound::new);
       return inventory.getInventory() >= quantity;
    }
    @Override
    public void purchase(Book book, int quantity) throws EntityNotFound, OutOfStockException {
        if (inStock(book, quantity)){
            Inventory inventory = inventoryRepository.findAllByBook(book).orElseThrow(EntityNotFound::new);
            inventory.setInventory(inventory.getInventory() - quantity);
        }else {
            throw new OutOfStockException(book.getTitle());
        }
        Inventory inventory = inventoryRepository.findAllByBook(book).orElseThrow(EntityNotFound::new);
    }
}
