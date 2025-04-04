package br.com.four.ecom.core.domains.products.resources;

import br.com.four.ecom.core.domains.products.inputs.ProductInput;
import br.com.four.ecom.core.domains.products.models.ProductModel;

public interface CreateProduct {

    ProductModel execute(ProductInput input);
}
