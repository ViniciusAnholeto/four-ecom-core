package br.com.four.ecom.core.domains.orders.resources;

import br.com.four.ecom.core.domains.orders.inputs.OrderInput;

public interface CommandProcessor {
    void execute(OrderInput input);
}
