package br.com.four.ecom.core.infrastructure.api.v1.request;

import br.com.four.ecom.core.domains.products.inputs.ProductSearchInput;
import lombok.Data;

@Data
public class ProductSearchRequest {
    private String name;
    private String category;
    private PriceRangeRequest priceRange;

    public ProductSearchInput toInput() {
        return ProductSearchInput.builder()
                .name(name)
                .category(category)
                .priceRange(priceRange != null ? priceRange.toInput() : null)
                .build();
    }
}
