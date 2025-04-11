package br.com.four.ecom.core.domains.orders.enums;

public enum OrderStatusEnum {
    PENDING(),
    PAID(),
    CANCELLED(),
    PAYMENT_PENDING(),;

    OrderStatusEnum() {
    }
}
