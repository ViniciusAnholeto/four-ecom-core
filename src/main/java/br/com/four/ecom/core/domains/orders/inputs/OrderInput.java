package br.com.four.ecom.core.domains.orders.inputs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderInput {
    private Long userId;
    private String items;
    private String payment;

}
