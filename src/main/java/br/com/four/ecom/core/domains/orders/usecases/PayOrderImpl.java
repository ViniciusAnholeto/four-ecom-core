package br.com.four.ecom.core.domains.orders.usecases;

import br.com.four.ecom.core.domains.orders.enums.OrderStatusEnum;
import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.OrderPaymentFailedException;
import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.InvalidOrderStatusException;
import br.com.four.ecom.core.domains.orders.exceptions.Exceptions.OrderNotFoundException;
import br.com.four.ecom.core.domains.orders.inputs.PaymentInput;
import br.com.four.ecom.core.domains.orders.models.OrderModel;
import br.com.four.ecom.core.domains.orders.ports.DatabasePort;
import br.com.four.ecom.core.domains.orders.ports.KafkaPort;
import br.com.four.ecom.core.domains.orders.resources.PayOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PayOrderImpl implements PayOrder {

    private final KafkaPort kafkaPort;
    private final DatabasePort databasePort;

    @Override
    public OrderModel execute(Long id, PaymentInput paymentInput) {
        Optional<OrderModel> order = databasePort.getOrderById(id);

        if (order.isPresent()) {
            OrderModel existingOrder = order.get();

            if (existingOrder.getStatus().equals(OrderStatusEnum.OPEN)) {
                existingOrder.setStatus(OrderStatusEnum.PAYMENT_PENDING);
            } else {
                throw new InvalidOrderStatusException(existingOrder.getStatus().name(), id);
            }

            Optional<OrderModel> updatedOrder = databasePort.updateOrder(existingOrder);

            kafkaPort.sendPaymentCommand(existingOrder.getOrderId());

            return updatedOrder.orElseThrow(() -> new OrderPaymentFailedException(id));
        } else {
            throw new OrderNotFoundException(id);
        }
    }
}
