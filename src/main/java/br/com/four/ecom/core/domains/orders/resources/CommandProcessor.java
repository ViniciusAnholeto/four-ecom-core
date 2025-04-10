package br.com.four.ecom.core.domains.orders.resources;

import br.com.four.ecom.core.domains.orders.inputs.CommandInput;

public interface CommandProcessor {
    void execute(CommandInput input);
}
