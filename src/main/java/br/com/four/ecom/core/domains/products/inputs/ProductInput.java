package br.com.four.ecom.core.domains.products.inputs;

import lombok.Builder;
import lombok.Data;

import java.util.Optional;
import java.util.UUID;

@Data
@Builder
public class ProductInput {
    private Optional<UUID> id;
    private String name;
    private Double price;
    private String description;
    private String category;
    private Integer stockQuantity;
}
