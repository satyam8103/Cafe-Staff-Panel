package com.cafe.Dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private UserDTO waiter;
    private TableDTO table;
    private String customerName;
    private String contactNumber;
    private String paymentType;   // "CASH", "CARD", "UPI"
    private String orderStatus;   // "PLACED", "SERVED", "PAID"
    private LocalDateTime createdAt;
    private List<OrderItemDTO> items;

}
