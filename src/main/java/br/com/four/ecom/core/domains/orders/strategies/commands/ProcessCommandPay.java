package br.com.four.ecom.core.domains.orders.strategies.commands;

import br.com.four.ecom.core.domains.orders.enums.OrderCommandEnum;
import br.com.four.ecom.core.domains.orders.enums.OrderStatusEnum;
import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.OrderPaymentFailedException;
import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.InvalidOrderStatusException;
import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.OrderNotFoundException;
import br.com.four.ecom.core.domains.orders.inputs.OrderInput;
import br.com.four.ecom.core.domains.orders.models.OrderModel;
import br.com.four.ecom.core.domains.orders.ports.DatabasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProcessCommandPay implements HasCommand {

    private final DatabasePort databasePort;

    @Override
    public OrderCommandEnum getCommand() {
        return OrderCommandEnum.PAY;
    }

    @Override
    public void execute(OrderInput orderInput) {
        Optional<OrderModel> orderToPay = databasePort.getOrderById(orderInput.getOrderId());

        if (orderToPay.isPresent()) {
            OrderModel existingOrder = orderToPay.get();

            switch (existingOrder.getStatus()) {
                case PAYMENT_PENDING:
                    existingOrder.setStatus(OrderStatusEnum.PAID);
                    break;
                case OPEN, PAID, CANCELLED:
                    throw new OrderPaymentFailedException(existingOrder.getOrderId());
                default:
                    throw new InvalidOrderStatusException(existingOrder.getStatus().name(),
                            existingOrder.getOrderId());
            }

            databasePort.updateOrder(existingOrder);

        } else {
            throw new OrderNotFoundException(orderInput.getOrderId());
        }
    }
}
