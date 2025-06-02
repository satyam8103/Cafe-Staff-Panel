package com.cafe.service.Interface;

import com.cafe.Dto.InventoryDTO;

import java.util.List;

public interface InventoryService {
    InventoryDTO addInventory(InventoryDTO inventoryDTO);
    InventoryDTO updateInventory(Long id, int quantity);
    List<InventoryDTO> getInventoryByCafe(Long cafeId);
    List<InventoryDTO> getAllInventory();
}
