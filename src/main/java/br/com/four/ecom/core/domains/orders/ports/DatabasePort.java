package br.com.four.ecom.core.domains.orders.ports;

import br.com.four.ecom.core.domains.orders.models.OrderModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DatabasePort {

    Optional<OrderModel> getOrderById(Long id);

    Optional<List<OrderModel>> getOrderByUserId(Long userId);

    Optional<OrderModel> createOrder(OrderModel orderModel);

    Optional<OrderModel> updateOrder(OrderModel orderModel);

    void updateProductQuantity(UUID productId, Integer quantity);

}
