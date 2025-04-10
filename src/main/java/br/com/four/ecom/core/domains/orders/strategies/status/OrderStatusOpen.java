package br.com.four.ecom.core.domains.orders.strategies.status;

import br.com.four.ecom.core.domains.orders.enums.OrderStatusEnum;
import br.com.four.ecom.core.domains.orders.inputs.OrderInput;
import br.com.four.ecom.core.domains.orders.models.OrderModel;
import br.com.four.ecom.core.domains.orders.models.OrderProductsModel;
import br.com.four.ecom.core.domains.orders.ports.DatabasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderStatusOpen implements HasStatus {

    private final DatabasePort databasePort;

    @Override
    public OrderStatusEnum getStatus() {
        return OrderStatusEnum.OPEN;
    }

    @Override
    public OrderModel execute(OrderModel existingOrder, OrderInput orderInput) {
        Optional<OrderModel> updatedOrder = databasePort.updateOrder(existingOrder, orderInput);

        return updatedOrder.orElseThrow(() -> new RuntimeException("Failed to update order"));
    }
}
