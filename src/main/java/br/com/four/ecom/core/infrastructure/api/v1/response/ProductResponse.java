package br.com.four.ecom.core.infrastructure.api.v1.response;

import br.com.four.ecom.core.domains.products.models.ProductModel;
import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private Double price;
    private String description;

    public ProductResponse(ProductModel model) {
        this.id = model.getId();
        this.name = model.getName();
        this.price = model.getPrice();
        this.description = model.getDescription();
    }
}
