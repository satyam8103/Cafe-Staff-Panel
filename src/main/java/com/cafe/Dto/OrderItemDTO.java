package com.cafe.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {
    private Long id;
    private OrderDTO order;
    private ProductDTO product;
    private int quantity;
}
