package br.com.four.ecom.core.domains.products.inputs;

import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Data
@Builder
public class ProductInput {
    private Optional<String> id;
    private String name;
    private Double price;
    private String description;
    private String category;
    private Integer quantity;
}
