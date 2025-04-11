package br.com.four.ecom.core.infrastructure.database;

import br.com.four.ecom.core.domains.products.inputs.ProductInput;
import br.com.four.ecom.core.domains.products.inputs.ProductSearchInput;
import br.com.four.ecom.core.domains.products.models.NewProductModel;
import br.com.four.ecom.core.domains.products.models.ProductModel;
import br.com.four.ecom.core.domains.products.ports.DatabasePort;
import br.com.four.ecom.core.infrastructure.database.entities.Product;
import br.com.four.ecom.core.infrastructure.database.entities.ProductDocument;
import br.com.four.ecom.core.infrastructure.database.repositories.ProductElasticsearchRepository;
import br.com.four.ecom.core.infrastructure.database.repositories.ProductRepository;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.search.Hit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductsDatabaseAdapter implements DatabasePort {

    private final ProductRepository productRepository;
    private final ProductElasticsearchRepository productElasticsearchRepository;
    private final ElasticsearchClient elasticsearchClient;

    @Override
    public ProductModel createProduct(NewProductModel newProduct) {
        log.info("Creating new product: {}", newProduct);

        Product savedProduct = productRepository.save(new Product(newProduct));

        productElasticsearchRepository.save(buildProductDocument(savedProduct));

        return savedProduct.toProductsModel();
    }

    @Override
    public ProductModel updateProduct(ProductInput input, ProductModel existingProduct) {
        log.info("Updating product: {} with values {}", existingProduct, input);

        ProductModel productToUpdate = ProductModel.builder()
                .id(existingProduct.getId())
                .name(input.getName().isEmpty()
                        ? existingProduct.getName()
                        : input.getName())
                .price(input.getPrice() == null
                        ? existingProduct.getPrice()
                        : input.getPrice())
                .description(input.getDescription().isEmpty()
                        ? existingProduct.getDescription()
                        : input.getDescription())
                .category(input.getCategory().isEmpty()
                        ? existingProduct.getCategory()
                        : input.getCategory())
                .quantity(input.getQuantity() == null
                        ? existingProduct.getQuantity()
                        : input.getQuantity())
                .createdAt(existingProduct.getCreatedAt())
                .build();

        Product updatedProduct = productRepository.save(new Product(productToUpdate));

        productElasticsearchRepository.save(buildProductDocument(updatedProduct));

        return updatedProduct.toProductsModel();
    }

    @Override
    public Optional<ProductModel> findProductById(String id) {
        log.info("Finding product by ID: {}", id);

        Optional<ProductDocument> productDocument = productElasticsearchRepository.findById(id);
        return productDocument.map(doc -> ProductModel.builder()
                .id(doc.getId())
                .name(doc.getName())
                .description(doc.getDescription())
                .category(doc.getCategory())
                .price(doc.getPrice())
                .quantity(doc.getQuantity())
                .createdAt(LocalDateTime.ofInstant(Instant.ofEpochMilli(doc.getCreatedAt()), ZoneId.systemDefault()))
                .updatedAt(LocalDateTime.ofInstant(Instant.ofEpochMilli(doc.getUpdatedAt()), ZoneId.systemDefault()))
                .build());
    }

    @Override
    public void deleteProduct(String id) {
        log.info("Deleting product by ID: {}", id);

        productRepository.deleteById(id);

        productElasticsearchRepository.deleteById(id);
    }

    @Override
    public Optional<List<ProductModel>> findProductByFilters(ProductSearchInput input) {
        log.info("Searching products with filters: {}", input);

        try {
            BoolQuery.Builder queryBuilder = new BoolQuery.Builder();

            if (input.getName() != null && !input.getName().isBlank()) {
                queryBuilder.must(Query.of(q -> q.fuzzy(f -> f
                        .field("name")
                        .value(input.getName()))));
            }

            if (input.getCategory() != null && !input.getCategory().isBlank()) {
                queryBuilder.filter(Query.of(q -> q.term(t -> t
                        .field("category")
                        .value(input.getCategory()))));
            }

            if (input.getPriceRange().getMin() != null) {
                queryBuilder.filter(Query.of(q -> q.range(r -> r
                        .number(n -> n.field("price").gte(input.getPriceRange().getMin())))));
            }

            if (input.getPriceRange().getMax() != null) {
                queryBuilder.filter(Query.of(q -> q.range(r -> r
                        .number(n -> n.field("price").lte(input.getPriceRange().getMax())))));
            }

            queryBuilder.filter(Query.of(q -> q.range(r -> r
                    .number(n -> n.field("quantity")
                            .gte((double) 0)))));

            var response = elasticsearchClient.search(s -> s
                    .index("products")
                    .size(30)
                    .query(q -> q.bool(queryBuilder.build())), ProductDocument.class);

            return Optional.of(response.hits().hits().stream()
                    .map(Hit::source)
                    .filter(Objects::nonNull)
                    .map(doc -> ProductModel.builder()
                            .id(doc.getId())
                            .name(doc.getName())
                            .description(doc.getDescription())
                            .category(doc.getCategory())
                            .price(doc.getPrice())
                            .quantity(doc.getQuantity())
                            .createdAt(LocalDateTime.ofInstant(Instant.ofEpochMilli(doc.getCreatedAt()), ZoneId.systemDefault()))
                            .updatedAt(LocalDateTime.ofInstant(Instant.ofEpochMilli(doc.getUpdatedAt()), ZoneId.systemDefault()))
                            .build())
                    .toList());
        } catch (Exception e) {
            log.error("Error searching products: {}", e.getMessage(), e);
            return Optional.empty();
        }
    }

    private ProductDocument buildProductDocument(Product product) {
        ProductDocument productDocument = new ProductDocument();
        productDocument.setId(product.getId());
        productDocument.setName(product.getName());
        productDocument.setDescription(product.getDescription());
        productDocument.setCategory(product.getCategory());
        productDocument.setPrice(product.getPrice());
        productDocument.setQuantity(product.getQuantity());
        productDocument.setCreatedAt(product.getCreatedAt().toInstant(ZoneOffset.UTC).toEpochMilli());
        productDocument.setUpdatedAt(product.getUpdatedAt().toInstant(ZoneOffset.UTC).toEpochMilli());

        return productDocument;
    }
}
