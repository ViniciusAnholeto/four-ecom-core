package br.com.four.ecom.core.infrastructure.api.v1.response;

import br.com.four.ecom.core.domains.products.models.ProductModel;
import lombok.Data;

import java.util.UUID;

@Data
public class ProductResponse {
    private UUID id;
    private String name;
    private Double price;
    private String description;
    private String category;
    private Integer stockQuantity;
    private String createdAt;
    private String updatedAt;

    public ProductResponse(ProductModel model) {
        this.id = model.getId();
        this.name = model.getName();
        this.price = model.getPrice();
        this.description = model.getDescription();
        this.category = model.getCategory();
        this.stockQuantity = model.getStockQuantity();
        this.createdAt = model.getCreatedAt();
        this.updatedAt = model.getUpdatedAt();
    }
}
