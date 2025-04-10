package br.com.four.ecom.core.infrastructure.api.v1.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderProductsModel {
    private String productId;
    private Integer quantity;
    private Double price;
}
