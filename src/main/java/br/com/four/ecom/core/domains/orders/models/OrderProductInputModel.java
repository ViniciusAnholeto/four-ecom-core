package br.com.four.ecom.core.domains.orders.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderProductInputModel {
    private String productId;
    private Integer quantity;
}
