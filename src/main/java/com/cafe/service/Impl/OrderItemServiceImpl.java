package com.cafe.service.Impl;

import com.cafe.Dto.OrderItemDTO;
import com.cafe.entity.Order;
import com.cafe.entity.OrderItem;
import com.cafe.entity.Product;
import com.cafe.exception.ResourceNotFoundException;
import com.cafe.repository.OrderItemRepository;
import com.cafe.repository.OrderRepository;
import com.cafe.repository.ProductRepository;
import com.cafe.service.Interface.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;

    @Override
    public OrderItemDTO addItemToOrder(OrderItemDTO dto) {
        Order order = orderRepository.findById(dto.getOrder().getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Order not found with id: %s", dto.getOrder().getId())));

        Product product = productRepository.findById(dto.getProduct().getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product not found with id: %s", dto.getProduct().getId())));

        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setProduct(product);
        item.setQuantity(dto.getQuantity());

        OrderItem saved = orderItemRepository.save(item);
        return modelMapper.map(saved, OrderItemDTO.class);
    }

    @Override
    public List<OrderItemDTO> getOrderItemsByOrder(Long orderId) {
        return orderItemRepository.findByOrderId(orderId)
                .stream()
                .map(item -> modelMapper.map(item, OrderItemDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderItemDTO> getAllOrderItems() {
        return orderItemRepository.findAll()
                .stream()
                .map(item -> modelMapper.map(item, OrderItemDTO.class))
                .collect(Collectors.toList());
    }
}
