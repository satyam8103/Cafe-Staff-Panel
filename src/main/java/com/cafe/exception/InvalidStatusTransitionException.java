package com.cafe.exception;

/**
 * Exception thrown when an invalid status transition is attempted.
 * This occurs when trying to change an order's status to a state that is not allowed
 * based on the current status.
 */
public class InvalidStatusTransitionException extends RuntimeException {
    
    /**
     * Constructs a new InvalidStatusTransitionException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidStatusTransitionException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidStatusTransitionException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     */
    public InvalidStatusTransitionException(String message, Throwable cause) {
        super(message, cause);
    }
} 