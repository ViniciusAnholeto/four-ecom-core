package br.com.four.ecom.core.domains.products.usecases;

import br.com.four.ecom.core.domains.products.inputs.ProductInput;
import br.com.four.ecom.core.domains.products.models.ProductModel;
import br.com.four.ecom.core.domains.products.resources.CreateOrUpdateProduct;

public class CreateOrUpdateProductImpl implements CreateOrUpdateProduct {

    @Override
    public ProductModel execute(ProductInput input) {
        // Implement the logic to create a product here
        // For example, you might want to save the product to a database
        // and return the created product model.
        return new ProductModel();
    }
}
