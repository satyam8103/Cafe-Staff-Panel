package com.cafe.controller;

import com.cafe.Dto.TableDTO;
import com.cafe.service.Interface.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tables")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @PostMapping
    public ResponseEntity<TableDTO> createTable(@RequestBody TableDTO tableDTO) {
        return new ResponseEntity<>(tableService.createTable(tableDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TableDTO>> getAllTables() {
        return ResponseEntity.ok(tableService.getAllTables());
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<TableDTO> updateStatus(@PathVariable Long id, @RequestParam String status) {
        return ResponseEntity.ok(tableService.updateStatus(id, status));
    }
}
