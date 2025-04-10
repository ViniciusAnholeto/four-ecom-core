package br.com.four.ecom.core.domains.orders.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductsModel {
    private String productId;
    private Integer quantity;
    private Double price;
}
