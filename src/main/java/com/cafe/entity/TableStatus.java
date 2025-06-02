package com.cafe.entity;

/**
 * Enum representing the status of a table in the cafe.
 */
public enum TableStatus {
    /**
     * Table is available for reservation or seating.
     */
    AVAILABLE(0),

    /**
     * Table is currently reserved.
     */
    RESERVED(1);

    private final int code;

    TableStatus(int code) {
        this.code = code;
    }

    /**
     * Gets the integer code associated with the table status.
     * @return the status code
     */
    public int getCode() {
        return code;
    }
}
