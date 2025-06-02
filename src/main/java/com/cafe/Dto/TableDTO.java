package com.cafe.Dto;

import lombok.Data;

@Data
public class TableDTO {
    private Long id;
    private int tableNumber;
    private String status;     // "AVAILABLE" or "RESERVED"
    private CafeDTO cafe;
}
