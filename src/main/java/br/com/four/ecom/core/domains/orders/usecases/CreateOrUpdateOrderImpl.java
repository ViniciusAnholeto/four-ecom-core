package br.com.four.ecom.core.domains.orders.usecases;

import br.com.four.ecom.core.domains.orders.enums.OrderStatusEnum;
import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.OrderCreationFailedException;
import br.com.four.ecom.core.domains.orders.inputs.OrderInput;
import br.com.four.ecom.core.domains.orders.models.CreateOrderModel;
import br.com.four.ecom.core.domains.orders.models.CreatedOrderModel;
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
        CreatedOrderModel orderCreated = databasePort.createOrder(new CreateOrderModel(orderInput));

        if (orderCreated == null) {
            throw new OrderCreationFailedException();
        }

        List<OrderProductsModel> products = new ArrayList<>();
        OrderProductsModel orderProduct = new OrderProductsModel();
        orderProduct.setProductId(orderCreated.getProductId());
        orderProduct.setQuantity(orderCreated.getQuantity());
        products.add(orderProduct);

        return OrderModel.builder()
                .orderId(orderCreated.getOrderId())
                .customerId(orderCreated.getCustomerId())
                .totalPrice(orderCreated.getPrice())
                .status(OrderStatusEnum.valueOf(orderCreated.getStatus()))
                .products(products)
                .createdAt(orderCreated.getCreatedAt())
                .updatedAt(orderCreated.getUpdatedAt())
                .build();
    }
}
