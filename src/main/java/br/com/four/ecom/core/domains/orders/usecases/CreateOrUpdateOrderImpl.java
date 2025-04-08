package br.com.four.ecom.core.domains.orders.usecases;

import br.com.four.ecom.core.domains.orders.enums.OrderStatusEnum;
import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.OrderCreationFailedException;
import br.com.four.ecom.core.domains.orders.inputs.OrderInput;
import br.com.four.ecom.core.domains.orders.models.OrderModel;
import br.com.four.ecom.core.domains.orders.models.OrderProductsModel;
import br.com.four.ecom.core.domains.orders.ports.DatabasePort;
import br.com.four.ecom.core.domains.orders.resources.CreateOrUpdateOrder;
import br.com.four.ecom.core.domains.orders.strategies.status.HasStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateOrUpdateOrderImpl implements CreateOrUpdateOrder {

    private final DatabasePort databasePort;
    private final List<HasStatus> orderStatusStrategies;

    @Override
    public OrderModel execute(OrderInput orderInput) {
        Optional<OrderModel> order = databasePort.getOrderById(orderInput.getOrderId());

        return order.isPresent()
                ? updateOrder(order.get(), orderInput)
                : createOrder(orderInput);
    }

    private OrderModel updateOrder(OrderModel existingOrder, OrderInput orderInput) {
        return orderStatusStrategies.stream()
                .filter(strategy -> strategy.getStatus().equals(existingOrder.getStatus()))
                .findFirst().orElseThrow(() -> new RuntimeException("No strategy found for order status"))
                .execute(existingOrder, orderInput);
    }

    private OrderModel createOrder(OrderInput orderInput) {
        List<OrderProductsModel> productToCreate = new ArrayList<>();
        productToCreate.add(OrderProductsModel.builder()
                .productId(orderInput.getProduct().getProductId())
                .quantity(orderInput.getProduct().getQuantity())
                .build());

        Optional<OrderModel> createdOrder = databasePort.createOrder(
                OrderModel.builder()
                        .userId(orderInput.getUserId())
                        .products(productToCreate)
                        .status(OrderStatusEnum.OPEN)
                        .build()
        );

        return createdOrder.orElseThrow(OrderCreationFailedException::new);
    }
}
