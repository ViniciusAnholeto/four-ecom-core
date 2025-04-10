package br.com.four.ecom.core.domains.orders.inputs;

import br.com.four.ecom.core.domains.orders.enums.OrderCommandEnum;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommandInput {
    private String orderId;
    private OrderCommandEnum command;
}
