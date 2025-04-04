package br.com.four.ecom.core.domains.orders.models;

import lombok.Data;

@Data
public class OrderModel {
    private Long id;
    private Long userId;
    private Double totalPrice;
    private String status;
}
