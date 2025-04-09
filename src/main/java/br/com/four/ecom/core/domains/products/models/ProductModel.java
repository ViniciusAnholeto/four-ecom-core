package br.com.four.ecom.core.domains.products.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductModel {
    private String id;
    private String name;
    private Double price;
    private String description;
    private String category;
    private Integer quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
