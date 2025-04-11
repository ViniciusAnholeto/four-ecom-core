package br.com.four.ecom.core.infrastructure.database.repositories;

import br.com.four.ecom.core.infrastructure.database.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Order, String> {
    List<Order> findAllByOrderId(String orderId);

    List<Order> findAllByCustomerId(String customerId);

    Optional<Order> findByOrderIdAndProductId(String orderId, String productId);

    @Query("SELECT o FROM Order o WHERE o.status = :status AND MONTH(o.createdAt) = :month AND YEAR(o.createdAt) = :year")
    List<Order> findByStatusAndMonth(String status, Integer month, Integer year);

    @Query("SELECT o FROM Order o WHERE o.status = 'PAID' AND o.customerId = :customerId AND o.createdAt BETWEEN :startDate AND :endDate")
    List<Order> findByUserIdAndDateRange(String customerId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT o.customerId, SUM(o.price * o.quantity) as totalSpent " +
            "FROM Order o " +
            "WHERE o.status = 'PAID' " +
            "AND o.createdAt BETWEEN :startDate AND :endDate " +
            "GROUP BY o.customerId " +
            "ORDER BY totalSpent DESC")
    List<Object[]> findTopBuyersByDateRange(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
}
