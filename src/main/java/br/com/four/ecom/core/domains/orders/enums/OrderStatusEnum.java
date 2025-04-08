package br.com.four.ecom.core.domains.orders.enums;

public enum OrderStatusEnum {
    OPEN("OPEN"),
    PAID("PAID"),
    CANCELLED("CANCELLED"),
    PAYMENT_PENDING("PAYMENT_PENDING"),;

    private final String status;

    OrderStatusEnum(String status) {
        this.status = status;
    }
}
