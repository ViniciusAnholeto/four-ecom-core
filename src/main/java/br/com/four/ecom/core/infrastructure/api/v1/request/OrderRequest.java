package br.com.four.ecom.core.infrastructure.api.v1.request;

import br.com.four.ecom.core.domains.orders.inputs.OrderInput;
import lombok.Data;

@Data
public class OrderRequest {
    private Long userId;
    private String items;
    private String payment;

    public OrderInput toInput() {
        return OrderInput.builder()
                .userId(userId)
                .items(items)
                .payment(payment)
                .build();
    }
}
