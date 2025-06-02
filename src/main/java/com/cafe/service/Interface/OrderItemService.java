package com.cafe.service.Interface;

import com.cafe.Dto.OrderItemDTO;

import java.util.List;

public interface OrderItemService {
    OrderItemDTO addItemToOrder(OrderItemDTO orderItemDTO);
    List<OrderItemDTO> getOrderItemsByOrder(Long orderId);
    List<OrderItemDTO> getAllOrderItems();
}
