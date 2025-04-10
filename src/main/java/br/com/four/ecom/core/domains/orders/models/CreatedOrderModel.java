package br.com.four.ecom.core.domains.orders.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CreatedOrderModel {
    private String orderId;
    private String customerId;
    private String status;
    private String productId;
    private Double price;
    private Integer quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
