package br.com.four.ecom.core.domains.products.models;

import br.com.four.ecom.core.domains.products.inputs.ProductInput;
import lombok.Data;

@Data
public class UpdateProductModel {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String category;
    private Integer stockQuantity;

    public UpdateProductModel(ProductInput productInput) {
        this.id = productInput.getId().orElse(null);
        this.name = productInput.getName();
        this.price = productInput.getPrice();
        this.description = productInput.getDescription();
        this.category = productInput.getCategory();
        this.stockQuantity = productInput.getStockQuantity();
    }
}
