package br.com.four.ecom.core.infrastructure.api.v1.request;

import br.com.four.ecom.core.domains.products.inputs.PriceRangeInput;
import lombok.Data;

@Data
public class PriceRangeRequest {
    private Double min;
    private Double max;

    public PriceRangeInput toInput() {
        return PriceRangeInput.builder()
                .min(min)
                .max(max)
                .build();
    }
}
