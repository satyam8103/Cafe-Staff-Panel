package com.cafe.controller;

import com.cafe.Dto.OrderItemDTO;
import com.cafe.service.Interface.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<OrderItemDTO> addItem(@RequestBody OrderItemDTO orderItemDTO) {
        return new ResponseEntity<>(orderItemService.addItemToOrder(orderItemDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderItemDTO>> getAllOrderItems() {
        return ResponseEntity.ok(orderItemService.getAllOrderItems());
    }
}
