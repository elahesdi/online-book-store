package com.TOSAN.onlineBookStore.service;

import com.TOSAN.onlineBookStore.model.Inventory;
import org.springframework.stereotype.Service;


public interface InventoryService {
    Inventory addInventory(Inventory inventory);
}
