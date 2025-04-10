package br.com.four.ecom.core.infrastructure.api.v1.request;

import br.com.four.ecom.core.domains.orders.inputs.PaymentInput;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PaymentRequest {
    @NotNull(message = "Order ID não pode ser nulo")
    private String orderId;

    @NotNull(message = "Método de pagamento não pode ser nulo")
    private String paymentMethod;

    public PaymentInput toInput() {
        return PaymentInput.builder()
                .id(orderId)
                .paymentMethod(paymentMethod)
                .build();
    }
}
