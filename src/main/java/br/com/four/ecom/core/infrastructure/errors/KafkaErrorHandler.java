package br.com.four.ecom.core.infrastructure.errors;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.RecordDeserializationException;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.kafka.listener.MessageListenerContainer;

@Slf4j
public class KafkaErrorHandler implements CommonErrorHandler {

    @Override
    public void handleOtherException(
            @NonNull Exception exception,
            @NonNull Consumer<?, ?> consumer,
            @NonNull MessageListenerContainer container, boolean batchListener) {
        handle(exception, consumer, null);
    }

    @Override
    public boolean handleOne(@NonNull Exception thrownException,
                             @NotNull ConsumerRecord<?, ?> consumerRecord,
                             @NotNull Consumer<?, ?> consumer, @NotNull MessageListenerContainer container) {
        return true;
    }

    private void handle(Exception exception, Consumer<?, ?> consumer,
                        ConsumerRecord<?, ?> consumerRecord) {
        if (exception instanceof RecordDeserializationException ex) {
            consumer.seek(ex.topicPartition(), ex.offset() + 1L);
            consumer.commitSync();
            log.error("Deserialize Kafka message error: {}", ex.getMessage());
        } else if (exception instanceof ListenerExecutionFailedException && consumerRecord != null) {
            final TopicPartition topicPartition = new TopicPartition(consumerRecord.topic(),
                    consumerRecord.partition());

            consumer.seek(topicPartition, consumerRecord.offset() + 1L);
            consumer.commitSync();
            log.error("Kafka Consumer error: {}", ExceptionUtils.getStackTrace(exception));
        } else {
            log.error("Kafka Exception not handled error: {}", ExceptionUtils.getStackTrace(exception));
        }

    }
}
