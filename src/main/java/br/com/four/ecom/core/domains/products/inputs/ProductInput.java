package br.com.four.ecom.core.domains.products.inputs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductInput {
    private String name;
    private Double price;
    private String description;
}
