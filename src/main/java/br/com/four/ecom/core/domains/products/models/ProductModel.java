package br.com.four.ecom.core.domains.products.models;

import lombok.Data;

import java.util.UUID;

@Data
public class ProductModel {
    private UUID id;
    private String name;
    private Double price;
    private String description;
    private String category;
    private Integer stockQuantity;
    private String createdAt;
    private String updatedAt;
}
