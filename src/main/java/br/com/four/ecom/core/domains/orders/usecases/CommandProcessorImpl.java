package br.com.four.ecom.core.domains.orders.usecases;

import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.InvalidCommandException;
import br.com.four.ecom.core.domains.orders.inputs.CommandInput;
import br.com.four.ecom.core.domains.orders.resources.CommandProcessor;
import br.com.four.ecom.core.domains.orders.strategies.commands.HasCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandProcessorImpl implements CommandProcessor {

    private final List<HasCommand> command;

    @Override
    public void execute(CommandInput input) {
        command.stream()
                .filter(c -> c.getCommand().equals(input.getCommand()))
                .findFirst()
                .orElseThrow(() ->
                        new InvalidCommandException(input.getCommand().name(), input.getOrderId()))
                .execute(input);

    }
}
