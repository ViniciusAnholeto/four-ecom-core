package br.com.four.ecom.core.infrastructure.api.v1.request;

import br.com.four.ecom.core.domains.orders.models.OrderProductInputModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.UUID;

@Data
public class OrderProductModel {

    @NotNull(message = "ID do produto não pode ser nulo")
    private UUID id;

    @Positive(message = "Quantidade de produtos precisa ser um valor positivo")
    private Integer quantity;

    public OrderProductInputModel toInput() {
        return OrderProductInputModel.builder()
                .id(id)
                .quantity(quantity)
                .build();
    }
}
