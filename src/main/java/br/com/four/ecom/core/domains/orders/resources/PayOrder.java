package br.com.four.ecom.core.domains.orders.resources;

import br.com.four.ecom.core.domains.orders.inputs.PaymentInput;
import br.com.four.ecom.core.domains.orders.models.OrderModel;

public interface PayOrder {
    OrderModel execute(PaymentInput paymentInput);
}
