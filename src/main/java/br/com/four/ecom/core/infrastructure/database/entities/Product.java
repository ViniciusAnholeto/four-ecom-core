package br.com.four.ecom.core.infrastructure.database.entities;

import br.com.four.ecom.core.domains.products.models.NewProductModel;
import br.com.four.ecom.core.domains.products.models.ProductModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    private String id;

    private String name;
    private Double price;
    private String description;
    private String category;
    private Integer quantity;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Product(NewProductModel newProduct) {
        this.id = UUID.randomUUID().toString();
        this.name = newProduct.getName();
        this.price = newProduct.getPrice();
        this.description = newProduct.getDescription();
        this.category = newProduct.getCategory();
        this.quantity = newProduct.getQuantity();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public ProductModel toModel() {
        return ProductModel.builder()
                .id(this.id)
                .name(this.name)
                .price(this.price)
                .description(this.description)
                .category(this.category)
                .quantity(this.quantity)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }

    public Product(ProductModel productModel) {
        this.id = productModel.getId();
        this.name = productModel.getName();
        this.price = productModel.getPrice();
        this.description = productModel.getDescription();
        this.category = productModel.getCategory();
        this.quantity = productModel.getQuantity();
        this.updatedAt = LocalDateTime.now();
    }
}
