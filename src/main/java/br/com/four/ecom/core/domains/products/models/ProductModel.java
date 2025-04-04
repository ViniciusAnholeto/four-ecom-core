package br.com.four.ecom.core.domains.products.models;

import lombok.Data;

@Data
public class ProductModel {
    private Long id;
    private String name;
    private Double price;
    private String description;
}
