package br.com.four.ecom.core.infrastructure.kafka.consumers;

import br.com.four.ecom.core.domains.orders.resources.CommandProcessor;
import br.com.four.ecom.core.infrastructure.kafka.dto.OrderCommandPayload;
import br.com.four.ecom.core.utils.EcomException.AsynchronousException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderCommandConsumer {

    private final CommandProcessor commandProcessor;

    @KafkaListener(topics = "${configs.kafka.topics.command.name}",
            groupId = "${configs.kafka.group-id}",
            properties = {
                    "spring.json.value.default.type:br.com.four.ecom.core.infrastructure.kafka.dto.OrderCommandPayload",
                    "spring.json.use.type.headers:false"})
    public void onListener(final ConsumerRecord<String, OrderCommandPayload> message,
                           final Acknowledgment ack) {
        try {
            log.info("Command received : {}", message.value().toString());

            commandProcessor.execute(message.value().toInput());

            ack.acknowledge();
        } catch (final AsynchronousException ex) {
            log.error("Kafka OrderCommandConsumer exception: {}", ExceptionUtils.getStackTrace(ex));
            ack.acknowledge();
        } catch (final Exception ex) {
            log.error("Unable to handle Asynchronous consumer Error: {}",
                    ExceptionUtils.getStackTrace(ex));
            ack.acknowledge();
        }
    }
}
