package br.com.four.ecom.core.domains.orders.usecases;

import br.com.four.ecom.core.domains.orders.inputs.PaymentInput;
import br.com.four.ecom.core.domains.orders.models.OrderModel;
import br.com.four.ecom.core.domains.orders.resources.PayOrder;

public class PayOrderImpl implements PayOrder {

    @Override
    public OrderModel execute(Long id, PaymentInput paymentInput) {
        // Logic to process payment for the order
        return new OrderModel();
    }
}
