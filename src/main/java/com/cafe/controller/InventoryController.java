package com.cafe.controller;

import com.cafe.Dto.InventoryDTO;
import com.cafe.service.Interface.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    public ResponseEntity<InventoryDTO> addInventory(@RequestBody InventoryDTO inventoryDTO) {
        return new ResponseEntity<>(inventoryService.addInventory(inventoryDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InventoryDTO>> getAllInventory() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> updateInventory(@PathVariable Long id, @RequestParam int quantity) {
        return ResponseEntity.ok(inventoryService.updateInventory(id, quantity));
    }
}
