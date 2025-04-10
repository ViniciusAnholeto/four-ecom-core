package br.com.four.ecom.core.infrastructure.api.v1.request;

import lombok.Getter;

@Getter
public enum OrderOperationRequestEnum {
    ADD("ADD"),
    REMOVE("REMOVE");

    private final String operation;

    OrderOperationRequestEnum(final String operation) {
        this.operation = operation;
    }
}
