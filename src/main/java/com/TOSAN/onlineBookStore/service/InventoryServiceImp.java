package com.TOSAN.onlineBookStore.service;

import com.TOSAN.onlineBookStore.model.Inventory;
import com.TOSAN.onlineBookStore.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
}
