package br.com.four.ecom.core.domains.products.usecases;

import br.com.four.ecom.core.domains.products.inputs.ProductInput;
import br.com.four.ecom.core.domains.products.models.NewProductModel;
import br.com.four.ecom.core.domains.products.models.ProductModel;
import br.com.four.ecom.core.domains.products.ports.DatabasePort;
import br.com.four.ecom.core.domains.products.resources.CreateOrUpdateProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreateOrUpdateProductImpl implements CreateOrUpdateProduct {

    private final DatabasePort databasePort;

    @Override
    public ProductModel execute(ProductInput input) {
        Optional<ProductModel> existingProduct = input.getId().isPresent()
                ? databasePort.findProductById(input.getId().get())
                : Optional.empty();

        return existingProduct.isPresent()
                ? updateProduct(input, existingProduct.get())
                : createProduct(input);
    }

    private ProductModel createProduct(ProductInput input) {
        return databasePort.createProduct(new NewProductModel(input));
    }

    private ProductModel updateProduct(ProductInput input, ProductModel existingProduct) {
        return databasePort.updateProduct(input, existingProduct);
    }
}
