package br.com.four.ecom.core.infrastructure.api.v1.request;

import br.com.four.ecom.core.domains.orders.inputs.PaymentInput;
import lombok.Data;

@Data
public class PaymentRequest {
    private Long id;
    private String paymentMethod;

    public PaymentInput toInput() {
        return PaymentInput.builder()
                .id(id)
                .paymentMethod(paymentMethod)
                .build();
    }
}
