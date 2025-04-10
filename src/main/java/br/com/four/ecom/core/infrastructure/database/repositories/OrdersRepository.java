package br.com.four.ecom.core.infrastructure.database.repositories;

import br.com.four.ecom.core.infrastructure.database.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrdersRepository extends JpaRepository<Order, String> {
    List<Order> findAllByOrderId(String orderId);

    List<Order> findAllByCustomerId(String customerId);

    Optional<Order> findByOrderIdAndProductId(String orderId, String productId);
}
