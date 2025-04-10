package br.com.four.ecom.core.domains.orders.strategies.commands;

import br.com.four.ecom.core.domains.orders.enums.OrderCommandEnum;
import br.com.four.ecom.core.domains.orders.enums.OrderStatusEnum;
import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.OrderNotFoundException;
import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.InvalidOrderStatusException;
import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.OrderCancelFailedException;
import br.com.four.ecom.core.domains.orders.inputs.OrderInput;
import br.com.four.ecom.core.domains.orders.models.OrderModel;
import br.com.four.ecom.core.domains.orders.ports.DatabasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProcessCommandCancel implements HasCommand {

    private final DatabasePort databasePort;

    @Override
    public OrderCommandEnum getCommand() {
        return OrderCommandEnum.CANCEL;
    }

    @Override
    public void execute(OrderInput orderInput) {
        Optional<OrderModel> orderToCancel = databasePort.getOrderById(orderInput.getOrderId());

        if (orderToCancel.isPresent()) {
            OrderModel existingOrder = orderToCancel.get();

            switch (existingOrder.getStatus()) {
                case OPEN, PAYMENT_PENDING:
                    existingOrder.setStatus(OrderStatusEnum.CANCELLED);
                    break;
                case PAID, CANCELLED:
                    throw new OrderCancelFailedException(existingOrder.getOrderId(),
                            existingOrder.getStatus().name());
                default:
                    throw new InvalidOrderStatusException(existingOrder.getStatus().name(),
                            existingOrder.getOrderId());
            }

            databasePort.updateOrderStatus(existingOrder.getOrderId(),
                    OrderStatusEnum.CANCELLED.name());

        } else {
            throw new OrderNotFoundException(orderInput.getOrderId());
        }
    }
}
