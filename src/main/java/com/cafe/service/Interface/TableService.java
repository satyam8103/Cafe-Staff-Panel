package com.cafe.service.Interface;

import com.cafe.Dto.TableDTO;

import java.util.List;

public interface TableService {
    TableDTO createTable(TableDTO tableDTO);
    TableDTO updateTable(Long id, TableDTO tableDTO);
    void deleteTable(Long id);
    TableDTO getTableById(Long id);
    List<TableDTO> getAllTablesByCafe(Long cafeId);
    List<TableDTO> getAllTables();
    TableDTO updateStatus(Long tableId, String status);
}
