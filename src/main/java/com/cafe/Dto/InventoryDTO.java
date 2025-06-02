package com.cafe.Dto;

import lombok.Data;

@Data
public class InventoryDTO {
    private Long id;
    private String productName;
    private int quantity;
    private CafeDTO cafe;
    private ProductDTO product;
}
