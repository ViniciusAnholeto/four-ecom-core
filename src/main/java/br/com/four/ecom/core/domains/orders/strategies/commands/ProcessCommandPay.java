package br.com.four.ecom.core.domains.orders.strategies.commands;

import br.com.four.ecom.core.domains.orders.enums.OrderCommandEnum;
import br.com.four.ecom.core.domains.orders.enums.OrderStatusEnum;
import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.InvalidOrderStatusException;
import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.OrderNotFoundException;
import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.OrderPaymentFailedException;
import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.ProductsUpdateException;
import br.com.four.ecom.core.domains.orders.inputs.CommandInput;
import br.com.four.ecom.core.domains.orders.models.OrderModel;
import br.com.four.ecom.core.domains.orders.ports.DatabasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class ProcessCommandPay implements HasCommand {

    private final DatabasePort databasePort;

    @Override
    public OrderCommandEnum getCommand() {
        return OrderCommandEnum.PAY;
    }

    @Override
    @Transactional
    public void execute(CommandInput commandInput) {
        OrderModel existingOrder = databasePort.getOrderById(commandInput.getOrderId())
                .orElseThrow(() -> new OrderNotFoundException(commandInput.getOrderId()));

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

        databasePort.updateOrderStatus(existingOrder.getOrderId(),
                OrderStatusEnum.PAID.name());

        for (var item : existingOrder.getProducts()) {
            try {
                databasePort.updateProductDatabase(item.getProductId(), item.getQuantity());
            } catch (Exception e) {
                throw new ProductsUpdateException(existingOrder.getOrderId());
            }
        }
    }
}
