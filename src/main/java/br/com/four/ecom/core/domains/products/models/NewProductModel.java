package br.com.four.ecom.core.domains.products.models;

import br.com.four.ecom.core.domains.products.inputs.ProductInput;
import lombok.Data;

@Data
public class NewProductModel {
    private String name;
    private Double price;
    private String description;
    private String category;
    private Integer quantity;

    public NewProductModel(ProductInput productInput) {
        this.name = productInput.getName();
        this.price = productInput.getPrice();
        this.description = productInput.getDescription();
        this.category = productInput.getCategory();
        this.quantity = productInput.getQuantity();
    }
}
