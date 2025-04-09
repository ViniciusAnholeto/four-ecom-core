package br.com.four.ecom.core.infrastructure.api.v1.request;

import br.com.four.ecom.core.domains.products.inputs.ProductInput;
import lombok.Data;

import java.util.Optional;

@Data
public class ProductRequest {
    private String id;
    private String name;
    private Double price;
    private String description;
    private String category;
    private Integer stockQuantity;

    public ProductInput toInput() {
        return ProductInput.builder()
                .id(Optional.ofNullable(id))
                .name(name)
                .price(price)
                .description(description)
                .category(category)
                .quantity(stockQuantity)
                .build();
    }
}
