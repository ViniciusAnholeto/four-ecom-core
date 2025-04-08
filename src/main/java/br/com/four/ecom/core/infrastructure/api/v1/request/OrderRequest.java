package br.com.four.ecom.core.infrastructure.api.v1.request;

import br.com.four.ecom.core.domains.orders.inputs.OrderInput;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import javax.annotation.RegEx;

@Data
public class OrderRequest {
    private Integer orderId;

    @NotNull(message = "User ID não pode ser nulo")
    private Long userId;

    @Valid
    @NotNull(message = "Insira ao menos um produto")
    private OrderProductModel product;

    @Valid
    @NotNull(message = "Insira a operação desejada. ADD ou REMOVE produtos")
    private OrderOperationEnum operation;

    public OrderInput toInput() {
        return OrderInput.builder()
                .orderId(orderId)
                .userId(userId)
                .product(product.toInput())
                .operation(operation.getOperation())
                .build();
    }
}
