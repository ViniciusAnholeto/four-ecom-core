package br.com.four.ecom.core.domains.orders.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OrderProductModel {
    private String id;
    private String name;
    private Double price;
    private String description;
    private String category;
    private Integer quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
