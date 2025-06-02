package com.cafe.repository;

import com.cafe.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    List<Inventory> findByCafeId(Long cafeId);
    Inventory findByProductNameAndCafeId(String productName, Long cafeId);
}
