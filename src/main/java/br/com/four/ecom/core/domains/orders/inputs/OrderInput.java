package br.com.four.ecom.core.domains.orders.inputs;

import br.com.four.ecom.core.domains.orders.models.OrderProductInputModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderInput {
    private Long orderId;
    private Long userId;
    private OrderProductInputModel product;
    private String operation;
}
