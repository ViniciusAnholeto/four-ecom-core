package br.com.four.ecom.core.domains.orders.inputs;

import br.com.four.ecom.core.domains.orders.enums.OrderOperationEnum;
import br.com.four.ecom.core.domains.orders.models.OrderProductInputModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderInput {
    private String orderId;
    private String customerId;
    private OrderProductInputModel product;
    private OrderOperationEnum operation;
}
