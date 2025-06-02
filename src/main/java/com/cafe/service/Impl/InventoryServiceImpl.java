package com.cafe.service.Impl;

import com.cafe.Dto.InventoryDTO;
import com.cafe.entity.Cafe;
import com.cafe.entity.Inventory;
import com.cafe.exception.ResourceNotFoundException;
import com.cafe.repository.CafeRepository;
import com.cafe.repository.InventoryRepository;
import com.cafe.service.Interface.InventoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;
    private final CafeRepository cafeRepository;
    private final ModelMapper modelMapper;

    @Override
    public InventoryDTO addInventory(InventoryDTO inventoryDTO) {
        Inventory entity = modelMapper.map(inventoryDTO, Inventory.class);

        Cafe cafe = cafeRepository.findById(inventoryDTO.getCafe().getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Cafe not found with id : %s", inventoryDTO.getCafe().getId())));

        entity.setCafe(cafe);
        Inventory saved = inventoryRepository.save(entity);
        return modelMapper.map(saved, InventoryDTO.class);
    }

    @Override
    public InventoryDTO updateInventory(Long id, int quantity) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Inventory not found with id : %s", id)));
        inventory.setQuantity(quantity);
        return modelMapper.map(inventoryRepository.save(inventory), InventoryDTO.class);
    }

    @Override
    public List<InventoryDTO> getInventoryByCafe(Long cafeId) {
        return inventoryRepository.findByCafeId(cafeId)
                .stream()
                .map(i -> modelMapper.map(i, InventoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<InventoryDTO> getAllInventory() {
        return inventoryRepository.findAll()
                .stream()
                .map(i -> modelMapper.map(i, InventoryDTO.class))
                .collect(Collectors.toList());
    }
}
