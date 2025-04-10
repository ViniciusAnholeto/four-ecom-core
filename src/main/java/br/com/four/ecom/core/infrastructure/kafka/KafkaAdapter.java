package br.com.four.ecom.core.infrastructure.kafka;

import br.com.four.ecom.core.domains.orders.enums.OrderCommandEnum;
import br.com.four.ecom.core.domains.orders.ports.KafkaPort;
import br.com.four.ecom.core.infrastructure.kafka.dto.OrderCommandPayload;
import br.com.four.ecom.core.infrastructure.kafka.producers.OrderCommandProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaAdapter implements KafkaPort {

     private final OrderCommandProducer orderCommandProducer;

    @Override
    public void sendCancelCommand(String orderId) {
        OrderCommandPayload orderCommand = new OrderCommandPayload();
        orderCommand.setOrderId(orderId);
        orderCommand.setCommand(OrderCommandEnum.CANCEL);

        orderCommandProducer.produce(orderCommand);
        log.info("Sent cancel command for order id: {}", orderId);
    }

    @Override
    public void sendPaymentCommand(String orderId) {
        OrderCommandPayload orderCommand = new OrderCommandPayload();
        orderCommand.setOrderId(orderId);
        orderCommand.setCommand(OrderCommandEnum.PAY);

        orderCommandProducer.produce(orderCommand);
        log.info("Sent pay command for order id: {}", orderId);
    }
}
