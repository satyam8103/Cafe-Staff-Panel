package com.cafe.entity;

/**
 * Enum representing the possible statuses of an order in the cafe system.
 * Each status represents a different stage in the order lifecycle.
 */
public enum OrderStatus {
    /**
     * Order has been placed but not yet served
     */

    PLACED("Order has been placed and is waiting to be served"),

    /**
     * Order has been served to the customer
     */
    SERVED("Order has been served to the customer"),

    /**
     * Order has been paid for and completed
     */
    PAID("Order has been paid for and completed");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    /**
     * Gets the description of the order status
     *
     * @return String containing the description of the status
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the status transition is valid
     *
     * @param newStatus The status to transition to
     * @return boolean indicating if the transition is valid
     */
    public boolean canTransitionTo(OrderStatus newStatus) {
        if (this == PLACED) {
            return newStatus == SERVED;
        } else if (this == SERVED) {
            return newStatus == PAID;
        }
        return false;
    }
}
