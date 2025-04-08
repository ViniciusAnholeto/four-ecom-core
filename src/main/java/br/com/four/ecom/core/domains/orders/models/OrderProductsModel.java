package br.com.four.ecom.core.domains.orders.models;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class OrderProductsModel {
    private UUID productId;
    private String productName;
    private Integer quantity;
}
