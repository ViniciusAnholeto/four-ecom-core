package br.com.four.ecom.core.infrastructure.kafka.dto;

import br.com.four.ecom.core.domains.orders.enums.OrderCommandEnum;
import br.com.four.ecom.core.domains.orders.inputs.CommandInput;
import jakarta.validation.Validation;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderCommandPayload {
    @NotNull
    private String orderId;

    @NotNull
    private OrderCommandEnum command;

    public CommandInput toInput() {
        if (Validation.buildDefaultValidatorFactory().getValidator().validate(this).isEmpty()) {
            return CommandInput.builder()
                    .orderId(orderId)
                    .command(command)
                    .build();
        } else {
            throw new ValidationException("Message validation fail");
        }
    }
}
