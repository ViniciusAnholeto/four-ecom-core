package br.com.four.ecom.core.domains.orders.enums;

import lombok.Getter;

@Getter
public enum OrderOperationEnum {
    ADD("ADD"),
    REMOVE("REMOVE");

    private final String operation;

    OrderOperationEnum(final String operation) {
        this.operation = operation;
    }
}
