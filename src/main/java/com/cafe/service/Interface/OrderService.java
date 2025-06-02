package com.cafe.service.Interface;

import com.cafe.Dto.OrderDTO;
import com.cafe.exception.ResourceNotFoundException;
import com.cafe.exception.InvalidStatusTransitionException;

import java.util.List;

/**
 * Service interface for managing orders in the cafe system.
 * Provides methods for creating, updating, and retrieving orders.
 */
public interface OrderService {
    /**
     * Places a new order in the system.
     *
     * @param orderDTO The order details to be created
     * @return OrderDTO containing the created order information
     * @throws ResourceNotFoundException if waiter or table is not found
     */
    OrderDTO placeOrder(OrderDTO orderDTO);

    /**
     * Updates the status of an existing order.
     *
     * @param id The ID of the order to update
     * @param status The new status for the order
     * @return OrderDTO containing the updated order information
     * @throws ResourceNotFoundException if order is not found
     * @throws InvalidStatusTransitionException if status transition is invalid
     */
    OrderDTO updateOrderStatus(Long id, String status);

    /**
     * Retrieves all orders for a specific waiter.
     *
     * @param waiterId The ID of the waiter
     * @return List of OrderDTO containing orders for the waiter
     */
    List<OrderDTO> getOrdersByWaiter(Long waiterId);

    /**
     * Retrieves all orders for a specific table.
     *
     * @param tableId The ID of the table
     * @return List of OrderDTO containing orders for the table
     */
    List<OrderDTO> getOrdersByTable(Long tableId);

    /**
     * Retrieves all orders in the system.
     *
     * @return List of OrderDTO containing all orders
     */
    List<OrderDTO> getAllOrders();
}
