package br.com.four.ecom.core.infrastructure.api.v1.request;

import br.com.four.ecom.core.domains.orders.enums.OrderOperationEnum;
import br.com.four.ecom.core.domains.orders.inputs.OrderInput;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequest {
    private String orderId;

    @NotNull(message = "User ID não pode ser nulo")
    private String customerId;

    @Valid
    @NotNull(message = "Insira ao menos um produto")
    private OrderProductModel product;

    @Valid
    @NotNull(message = "Insira a operação desejada. ADD ou REMOVE produtos")
    private OrderOperationRequestEnum operation;

    public OrderInput toInput() {
        return OrderInput.builder()
                .orderId(orderId)
                .customerId(customerId)
                .product(product.toInput())
                .operation(OrderOperationEnum.valueOf(operation.getOperation()))
                .build();
    }
}
