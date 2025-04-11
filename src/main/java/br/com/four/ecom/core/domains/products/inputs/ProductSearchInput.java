package br.com.four.ecom.core.domains.products.inputs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductSearchInput {
    private String name;
    private String category;
    private PriceRangeInput priceRange;
}
