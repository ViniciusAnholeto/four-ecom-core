package br.com.four.ecom.core.domains.orders.strategies.status;

import br.com.four.ecom.core.domains.orders.enums.OrderStatusEnum;
import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.InvalidOrderStatusException;
import br.com.four.ecom.core.domains.orders.inputs.OrderInput;
import br.com.four.ecom.core.domains.orders.models.OrderModel;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusPaymentPending implements HasStatus {

    @Override
    public OrderStatusEnum getStatus() {
        return OrderStatusEnum.PAYMENT_PENDING;
    }

    @Override
    public OrderModel execute(OrderModel existingOrder, OrderInput orderInput) {
        throw new InvalidOrderStatusException(
                OrderStatusEnum.PAYMENT_PENDING.toString(), orderInput.getOrderId());
    }
}
