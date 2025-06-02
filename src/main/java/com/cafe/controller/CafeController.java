package com.cafe.controller;

import com.cafe.Dto.CafeDTO;
import com.cafe.service.Interface.CafeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cafes")
@RequiredArgsConstructor
public class CafeController {

    private final CafeService cafeService;

    @PostMapping
    public ResponseEntity<CafeDTO> createCafe(@RequestBody CafeDTO cafeDTO) {
        return new ResponseEntity<>(cafeService.createCafe(cafeDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CafeDTO>> getAllCafes() {
        return ResponseEntity.ok(cafeService.getAllCafes());
    }
}
