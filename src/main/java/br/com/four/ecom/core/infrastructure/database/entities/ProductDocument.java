package br.com.four.ecom.core.infrastructure.database.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "products")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDocument {
    @Id
    private String id;
    private String name;
    private String description;
    private String category;
    private Double price;
    private Integer quantity;
    private Long createdAt;
    private Long updatedAt;
}
