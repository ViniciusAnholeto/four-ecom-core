package br.com.four.ecom.core.domains.orders.resources;

import br.com.four.ecom.core.domains.orders.inputs.OrderInput;
import br.com.four.ecom.core.domains.orders.models.OrderModel;

public interface CreateOrUpdateOrder {
    OrderModel execute(OrderInput orderModel);
}
