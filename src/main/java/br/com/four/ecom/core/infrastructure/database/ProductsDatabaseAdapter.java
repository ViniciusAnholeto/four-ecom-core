package br.com.four.ecom.core.infrastructure.database;

import br.com.four.ecom.core.domains.products.inputs.ProductInput;
import br.com.four.ecom.core.domains.products.models.NewProductModel;
import br.com.four.ecom.core.domains.products.models.ProductModel;
import br.com.four.ecom.core.domains.products.ports.DatabasePort;
import br.com.four.ecom.core.infrastructure.database.entities.Product;
import br.com.four.ecom.core.infrastructure.database.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductsDatabaseAdapter implements DatabasePort {

    private final ProductRepository productRepository;

    @Override
    public ProductModel createProduct(NewProductModel newProduct) {
        log.info("Creating new product: {}", newProduct);

        return productRepository.save(new Product(newProduct)).toModel();
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

        return productRepository.save(new Product(productToUpdate)).toModel();
    }

    @Override
    public Optional<ProductModel> findProductById(String id) {
        log.info("Finding product by ID: {}", id);

        Optional<Product> product = productRepository.findById(id);

        return product.map(Product::toModel);
    }

    @Override
    public void deleteProduct(String id) {
        log.info("Deleting product by ID: {}", id);

        productRepository.deleteById(id);
    }
}
