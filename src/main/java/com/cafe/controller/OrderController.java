package com.cafe.controller;

import com.cafe.Dto.OrderDTO;
import com.cafe.service.Interface.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST Controller for managing orders in the cafe system.
 * Provides endpoints for creating, updating, and retrieving orders.
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * Creates a new order in the system.
     *
     * @param orderDTO The order details to be created
     * @return ResponseEntity containing the created order and status
     */
    @PostMapping
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody OrderDTO orderDTO) {
        logger.info("Creating new order for customer: {}", orderDTO.getCustomerName());
        OrderDTO createdOrder = orderService.placeOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                    "status", "success",
                    "message", "Order created successfully",
                    "data", createdOrder
                ));
    }

    /**
     * Updates the status of an existing order.
     *
     * @param id The ID of the order to update
     * @param status The new status for the order
     * @return ResponseEntity containing the updated order
     */
    @PostMapping("/{id}/status")
    public ResponseEntity<Map<String, Object>> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        logger.info("Updating order status for order ID: {} to status: {}", id, status);
        OrderDTO updatedOrder = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Order status updated successfully",
            "data", updatedOrder
        ));
    }

    /**
     * Retrieves all orders in the system.
     *
     * @return ResponseEntity containing the list of all orders
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllOrders() {
        logger.info("Retrieving all orders");
        List<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(Map.of(
            "status", "success",
            "message", "Orders retrieved successfully",
            "data", orders
        ));
    }
}
