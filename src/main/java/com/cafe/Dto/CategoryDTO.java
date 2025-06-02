package com.cafe.Dto;

import lombok.Getter;

@Getter
public class CategoryDTO {
    private Long id;
    private String name;
    private CafeDTO cafe;
}
