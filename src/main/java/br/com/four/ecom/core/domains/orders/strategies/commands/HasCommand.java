package br.com.four.ecom.core.domains.orders.strategies.commands;

import br.com.four.ecom.core.domains.orders.enums.OrderCommandEnum;
import br.com.four.ecom.core.domains.orders.inputs.CommandInput;

public interface HasCommand {

    OrderCommandEnum getCommand();

    void execute(CommandInput orderInput);
}
