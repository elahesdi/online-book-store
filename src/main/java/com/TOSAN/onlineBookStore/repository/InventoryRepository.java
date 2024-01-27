package com.TOSAN.onlineBookStore.repository;

import com.TOSAN.onlineBookStore.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
