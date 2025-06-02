package com.cafe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "inventory")
@Getter
@Setter
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;
    
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Inventory() {}
    public Inventory(Long id, String productName, Integer quantity, Cafe cafe, Product product) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.cafe = cafe;
        this.product = product;
    }
    // Getters & Setters
}
