package com.cafe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

/**
 * Entity class representing an order in the cafe system.
 * Contains information about the order, including customer details,
 * waiter assignment, table assignment, and order items.
 */
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "waiter_id", nullable = false)
    private User waiter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id", nullable = false)
    private TableEntity table;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "contact_number")
    private String contactNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type", nullable = false)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus = OrderStatus.PLACED;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<OrderItem> orderItems;

    /**
     * Calculates the total amount of the order.
     *
     * @return the total amount of the order
     */
    @Transient
    public double getTotalAmount() {
        return orderItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice().doubleValue() * item.getQuantity())
                .sum();
    }

    /**
 * Updates order status if transition is valid.
 *
 * @param newStatus the new status to set
 * @return true if the transition is successful, false otherwise
 */
public boolean updateOrderStatus(OrderStatus newStatus) {
    if (this.orderStatus.canTransitionTo(newStatus)) {
        this.orderStatus = newStatus;
        return true;
    }
    return false;
}

}

