package br.com.four.ecom.core.infrastructure.api.v1.request;

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
