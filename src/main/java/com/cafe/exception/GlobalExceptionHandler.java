package com.cafe.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the application.
 * Provides consistent error responses across all controllers.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Handles ResourceNotFoundException.
     *
     * @param ex the exception
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        logger.error("Resource not found: {}", ex.getMessage());
        return createErrorResponse(HttpStatus.NOT_FOUND, "Resource Not Found", ex.getMessage());
    }

    /**
     * Handles InvalidStatusTransitionException.
     *
     * @param ex the exception
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(InvalidStatusTransitionException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidStatusTransitionException(InvalidStatusTransitionException ex) {
        logger.error("Invalid status transition: {}", ex.getMessage());
        return createErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Status Transition", ex.getMessage());
    }

    /**
     * Handles IllegalArgumentException.
     *
     * @param ex the exception
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        logger.error("Illegal argument: {}", ex.getMessage());
        return createErrorResponse(HttpStatus.BAD_REQUEST, "Invalid Argument", ex.getMessage());
    }

    /**
     * Handles all other exceptions.
     *
     * @param ex the exception
     * @return ResponseEntity with error details
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAllUncaughtException(Exception ex) {
        logger.error("Unexpected error occurred: ", ex);
        return createErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR,
            "Internal Server Error",
            "An unexpected error occurred. Please try again later."
        );
    }

    /**
     * Creates a standardized error response.
     *
     * @param status the HTTP status
     * @param error the error type
     * @param message the error message
     * @return ResponseEntity with error details
     */
    private ResponseEntity<Map<String, Object>> createErrorResponse(
            HttpStatus status,
            String error,
            String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", message);
        return new ResponseEntity<>(body, status);
    }
}
