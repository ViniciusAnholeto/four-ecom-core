package br.com.four.ecom.core.domains.orders.ports;

import br.com.four.ecom.core.domains.orders.inputs.OrderInput;
import br.com.four.ecom.core.domains.orders.models.CreateOrderModel;
import br.com.four.ecom.core.domains.orders.models.CreatedOrderModel;
import br.com.four.ecom.core.domains.orders.models.OrderModel;

import java.util.List;
import java.util.Optional;

public interface DatabasePort {

    Optional<OrderModel> getOrderById(String id);

    Optional<List<OrderModel>> getOrderByCustomerId(String customerId);

    CreatedOrderModel createOrder(CreateOrderModel createOrderModel);

    Optional<OrderModel> updateOrder(OrderModel existingOrder, OrderInput orderInput);

    void updateProductDatabase(String productId, Integer quantity);

    Optional<OrderModel> updateOrderStatus(String orderId, String status);

}
