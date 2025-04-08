package br.com.four.ecom.core.infrastructure.api.v1.response;

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
