package com.cafe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tables")
public class TableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer tableNumber;

    @Enumerated(EnumType.STRING)
    private TableStatus status = TableStatus.AVAILABLE;

    @ManyToOne
    @JoinColumn(name = "cafe_id")
    private Cafe cafe;

    // Getters & Setters

}
