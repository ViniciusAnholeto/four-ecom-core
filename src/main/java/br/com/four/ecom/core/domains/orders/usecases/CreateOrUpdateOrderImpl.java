package br.com.four.ecom.core.domains.orders.usecases;

import br.com.four.ecom.core.domains.orders.inputs.OrderInput;
import br.com.four.ecom.core.domains.orders.models.OrderModel;
import br.com.four.ecom.core.domains.orders.resources.CreateOrUpdateOrder;

public class CreateOrUpdateOrderImpl implements CreateOrUpdateOrder {

    @Override
    public OrderModel execute(OrderInput orderModel) {
        // Implement the logic to create or update an order
        return new OrderModel();
    }
}
