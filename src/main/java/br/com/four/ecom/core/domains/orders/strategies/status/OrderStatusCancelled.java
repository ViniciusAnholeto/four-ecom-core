package br.com.four.ecom.core.domains.orders.strategies.status;

import br.com.four.ecom.core.domains.orders.enums.OrderStatusEnum;
import br.com.four.ecom.core.domains.orders.exceptions.Exceptions;
import br.com.four.ecom.core.domains.orders.inputs.OrderInput;
import br.com.four.ecom.core.domains.orders.models.OrderModel;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusCancelled implements HasStatus {

    @Override
    public OrderStatusEnum getStatus() {
        return OrderStatusEnum.CANCELLED;
    }

    @Override
    public OrderModel execute(OrderModel existingOrder, OrderInput orderInput) {
        throw new Exceptions.InvalidOrderStatusException(OrderStatusEnum.CANCELLED.toString(), orderInput.getOrderId());
    }
}
