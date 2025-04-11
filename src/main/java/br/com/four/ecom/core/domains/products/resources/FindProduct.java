package br.com.four.ecom.core.domains.products.resources;

import br.com.four.ecom.core.domains.products.inputs.ProductSearchInput;
import br.com.four.ecom.core.domains.products.models.ProductModel;

import java.util.List;

public interface FindProduct {
    List<ProductModel> execute(ProductSearchInput input);
}
