package br.com.four.ecom.core.domains.orders.strategies.commands;

import br.com.four.ecom.core.domains.orders.enums.OrderCommandEnum;
import br.com.four.ecom.core.domains.orders.inputs.OrderInput;

public interface HasCommand {

    OrderCommandEnum getCommand();

    void execute(OrderInput orderInput);
}
