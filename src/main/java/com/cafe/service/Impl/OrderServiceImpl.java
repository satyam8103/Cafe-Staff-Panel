package com.cafe.service.Impl;

import com.cafe.Dto.OrderDTO;
import com.cafe.entity.Order;
import com.cafe.entity.OrderStatus;
import com.cafe.entity.TableEntity;
import com.cafe.entity.User;
import com.cafe.exception.ResourceNotFoundException;
import com.cafe.exception.InvalidStatusTransitionException;
import com.cafe.repository.OrderRepository;
import com.cafe.repository.TableRepository;
import com.cafe.repository.UserRepository;
import com.cafe.service.Interface.OrderService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the OrderService interface.
 * Handles business logic for order management in the cafe system.
 */
@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TableRepository tableRepository;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Places a new order in the system.
     *
     * @param orderDTO The order details to be created
     * @return OrderDTO containing the created order information
     * @throws ResourceNotFoundException if waiter or table is not found
     */
    @Override
    @Transactional
    public OrderDTO placeOrder(OrderDTO orderDTO) {
        logger.info("Placing new order for customer: {}", orderDTO.getCustomerName());
        
        Order order = modelMapper.map(orderDTO, Order.class);

        User waiter = userRepository.findById(orderDTO.getWaiter().getId())
                .orElseThrow(() -> {
                    logger.error("Waiter not found with id: {}", orderDTO.getWaiter().getId());
                    return new ResourceNotFoundException("Waiter not found with id: " + orderDTO.getWaiter().getId());
                });

        TableEntity table = tableRepository.findById(orderDTO.getTable().getId())
                .orElseThrow(() -> {
                    logger.error("Table not found with id: {}", orderDTO.getTable().getId());
                    return new ResourceNotFoundException("Table not found with id: " + orderDTO.getTable().getId());
                });

        order.setWaiter(waiter);
        order.setTable(table);
        order.setOrderStatus(OrderStatus.PLACED);

        Order saved = orderRepository.save(order);
        logger.info("Order placed successfully with id: {}", saved.getId());
        return modelMapper.map(saved, OrderDTO.class);
    }

    /**
     * Updates the status of an existing order.
     *
     * @param id The ID of the order to update
     * @param status The new status for the order
     * @return OrderDTO containing the updated order information
     * @throws ResourceNotFoundException if order is not found
     * @throws InvalidStatusTransitionException if status transition is invalid
     */
    @Override
    @Transactional
    public OrderDTO updateOrderStatus(Long id, String status) {
        logger.info("Updating order status for order ID: {} to status: {}", id, status);
        
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("Order not found with id: {}", id);
                    return new ResourceNotFoundException("Order not found with id: " + id);
                });

        OrderStatus newStatus;
        try {
            newStatus = OrderStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error("Invalid order status: {}", status);
            throw new IllegalArgumentException("Invalid order status: " + status + ". Allowed values are PLACED, SERVED, PAID.");
        }

        if (!order.getOrderStatus().canTransitionTo(newStatus)) {
            logger.error("Invalid status transition from {} to {} for order {}", 
                order.getOrderStatus(), newStatus, id);
            throw new InvalidStatusTransitionException(
                String.format("Cannot transition order status from %s to %s", 
                    order.getOrderStatus(), newStatus));
        }

        order.setOrderStatus(newStatus);
        Order updated = orderRepository.save(order);
        logger.info("Order status updated successfully for order ID: {}", id);
        return modelMapper.map(updated, OrderDTO.class);
    }

    /**
     * Retrieves all orders in the system.
     *
     * @return List of OrderDTO containing all orders
     */
    @Override
    public List<OrderDTO> getAllOrders() {
        logger.info("Retrieving all orders");
        return orderRepository.findAll()
                .stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all orders for a specific waiter.
     *
     * @param waiterId The ID of the waiter
     * @return List of OrderDTO containing orders for the waiter
     */
    @Override
    public List<OrderDTO> getOrdersByWaiter(Long waiterId) {
        logger.info("Retrieving orders for waiter ID: {}", waiterId);
        return orderRepository.findByWaiterId(waiterId)
                .stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all orders for a specific table.
     *
     * @param tableId The ID of the table
     * @return List of OrderDTO containing orders for the table
     */
    @Override
    public List<OrderDTO> getOrdersByTable(Long tableId) {
        logger.info("Retrieving orders for table ID: {}", tableId);
        return orderRepository.findByTableId(tableId)
                .stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }
}
