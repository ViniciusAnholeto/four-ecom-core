package br.com.four.ecom.core.infrastructure.kafka.producers;

import br.com.four.ecom.core.infrastructure.kafka.dto.OrderCommandPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCommandProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${configs.kafka.topics.command.name}")
    private String orderCommandTopic;

    public void produce(final OrderCommandPayload orderCommand) {

        final Message<OrderCommandPayload> message = MessageBuilder.withPayload(orderCommand)
                .setHeader("version", "1.0.0")
                .setHeader(KafkaHeaders.TOPIC, orderCommandTopic)
                .setHeader(KafkaHeaders.KEY, orderCommand.getOrderId())
                .build();

    kafkaTemplate.send(message);

    log.info(String.format("Order command produced for order id: %s", orderCommand.getOrderId()));

    log.info(String.format("Order command message produced: %s", message.getPayload()));
    }
}
