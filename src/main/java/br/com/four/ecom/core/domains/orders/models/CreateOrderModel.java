package br.com.four.ecom.core.domains.orders.models;

import br.com.four.ecom.core.domains.orders.inputs.OrderInput;
import lombok.Data;

@Data
public class CreateOrderModel {
    private String customerId;
    private String productId;
    private Integer quantity;

    public CreateOrderModel(OrderInput input) {
        this.customerId = input.getCustomerId();
        this.productId = input.getProduct().getProductId();
        this.quantity = input.getProduct().getQuantity();
    }
}
