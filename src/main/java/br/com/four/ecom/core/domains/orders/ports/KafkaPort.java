package br.com.four.ecom.core.domains.orders.ports;

public interface KafkaPort {
    void sendCancelCommand(Long orderId);

    void sendPaymentCommand(Long orderId);
}
