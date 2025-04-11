package br.com.four.ecom.core.infrastructure.api.v1.response;

import br.com.four.ecom.core.domains.products.models.ProductModel;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
public class ProductResponse {
    private UUID id;
    private String name;
    private Double price;
    private String description;
    private String category;
    private Integer quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductResponse(ProductModel model) {
        this.id = UUID.fromString(model.getId());
        this.name = model.getName();
        this.price = model.getPrice();
        this.description = model.getDescription();
        this.category = model.getCategory();
        this.quantity = model.getQuantity();
        this.createdAt = model.getCreatedAt();
        this.updatedAt = model.getUpdatedAt();
    }

    public static List<ProductResponse> toList(List<ProductModel> models) {
        return models.stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }
}
