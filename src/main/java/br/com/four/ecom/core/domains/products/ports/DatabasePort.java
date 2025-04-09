package br.com.four.ecom.core.domains.products.ports;

import br.com.four.ecom.core.domains.products.inputs.ProductInput;
import br.com.four.ecom.core.domains.products.models.NewProductModel;
import br.com.four.ecom.core.domains.products.models.ProductModel;

import java.util.Optional;

public interface DatabasePort {
    Optional<ProductModel> findProductById(String id);

    ProductModel createProduct(NewProductModel product);

    ProductModel updateProduct(ProductInput input, ProductModel existingProduct);

    void deleteProduct(String id);
}
