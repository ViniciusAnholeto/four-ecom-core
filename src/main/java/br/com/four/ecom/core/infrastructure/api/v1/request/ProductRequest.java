package br.com.four.ecom.core.infrastructure.api.v1.request;

import br.com.four.ecom.core.domains.products.inputs.ProductInput;
import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private Double price;
    private String description;

    public ProductInput toInput() {
        return ProductInput.builder()
                .name(name)
                .price(price)
                .description(description)
                .build();
    }
}
