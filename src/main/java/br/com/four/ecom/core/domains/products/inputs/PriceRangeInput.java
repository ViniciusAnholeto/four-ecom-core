package br.com.four.ecom.core.domains.products.inputs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceRangeInput {
    private Double min;
    private Double max;
}
