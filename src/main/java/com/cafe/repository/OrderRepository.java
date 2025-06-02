package com.cafe.repository;

import com.cafe.entity.Order;
import com.cafe.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * Repository interface for managing Order entities in the database.
 * Provides methods for basic CRUD operations and custom queries.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    
    /**
     * Finds all orders associated with a specific waiter.
     *
     * @param waiterId The ID of the waiter
     * @return List of orders associated with the waiter
     */
    List<Order> findByWaiterId(Long waiterId);

    /**
     * Finds all orders associated with a specific table.
     *
     * @param tableId The ID of the table
     * @return List of orders associated with the table
     */
    List<Order> findByTableId(Long tableId);

    /**
     * Finds all orders with a specific status.
     *
     * @param status The status to search for
     * @return List of orders with the specified status
     */
    List<Order> findByOrderStatus(OrderStatus status);

    /**
     * Finds all orders created within a specific time range.
     *
     * @param startTime The start time of the range
     * @param endTime The end time of the range
     * @return List of orders created within the time range
     */
    @Query("SELECT o FROM Order o WHERE o.createdAt BETWEEN ?1 AND ?2")
    List<Order> findByCreatedAtBetween(Timestamp startTime, Timestamp endTime);
}
