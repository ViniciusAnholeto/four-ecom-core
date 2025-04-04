package br.com.four.ecom.core.domains.orders.inputs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentInput {
    private Long id;
    private String paymentMethod;
}
