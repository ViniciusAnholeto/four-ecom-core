package br.com.four.ecom.core.domains.orders.ports;

public interface KafkaPort {
    void sendCancelCommand(String orderId);

    void sendPaymentCommand(String orderId);
}
