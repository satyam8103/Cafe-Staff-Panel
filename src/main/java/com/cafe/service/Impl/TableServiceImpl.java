package com.cafe.service.Impl;

import com.cafe.Dto.TableDTO;
import com.cafe.entity.Cafe;
import com.cafe.entity.TableEntity;
import com.cafe.entity.TableStatus;
import com.cafe.exception.ResourceNotFoundException;
import com.cafe.repository.CafeRepository;
import com.cafe.repository.TableRepository;
import com.cafe.service.Interface.TableService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {

    private final TableRepository tableRepository;
    private final CafeRepository cafeRepository;
    private final ModelMapper modelMapper;

    @Override
    public TableDTO createTable(TableDTO tableDTO) {
        Cafe cafe = cafeRepository.findById(tableDTO.getCafe().getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Cafe not found with id: %s", tableDTO.getCafe().getId())));

        TableEntity table = modelMapper.map(tableDTO, TableEntity.class);
        table.setCafe(cafe);
        TableEntity saved = tableRepository.save(table);
        return modelMapper.map(saved, TableDTO.class);
    }

    @Override
    public TableDTO updateTable(Long id, TableDTO tableDTO) {
        TableEntity table = tableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Table not found with id: %s", id)));
        table.setTableNumber(tableDTO.getTableNumber());
        try {
            table.setStatus(TableStatus.valueOf(tableDTO.getStatus().toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Invalid table status: %s. Allowed values are AVAILABLE, RESERVED.", tableDTO.getStatus()));
        }

        TableEntity updated = tableRepository.save(table);
        return modelMapper.map(updated, TableDTO.class);
    }

    @Override
    public void deleteTable(Long id) {
        tableRepository.deleteById(id);
    }

    @Override
    public TableDTO getTableById(Long id) {
        TableEntity table = tableRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Table not found with id: %s", id)));
        return modelMapper.map(table, TableDTO.class);
    }

    @Override
    public List<TableDTO> getAllTablesByCafe(Long cafeId) {
        return tableRepository.findByCafeId(cafeId)
                .stream()
                .map(table -> modelMapper.map(table, TableDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TableDTO> getAllTables() {
        return tableRepository.findAll()
                .stream()
                .map(table -> modelMapper.map(table, TableDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TableDTO updateStatus(Long tableId, String status) {
        TableEntity table = tableRepository.findById(tableId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Table not found with id: %s", tableId)));
        try {
            table.setStatus(TableStatus.valueOf(status.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Invalid table status: %s. Allowed values are AVAILABLE, RESERVED.", status));
        }
        return modelMapper.map(tableRepository.save(table), TableDTO.class);
    }
}
