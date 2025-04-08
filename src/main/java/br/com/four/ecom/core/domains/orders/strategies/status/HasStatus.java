package br.com.four.ecom.core.domains.orders.strategies.status;

import br.com.four.ecom.core.domains.orders.enums.OrderStatusEnum;
import br.com.four.ecom.core.domains.orders.inputs.OrderInput;
import br.com.four.ecom.core.domains.orders.models.OrderModel;

public interface HasStatus {

    OrderStatusEnum getStatus();

    OrderModel execute(OrderModel orderModel, OrderInput orderInput);
}
