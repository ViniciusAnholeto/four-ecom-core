package br.com.four.ecom.core.domains.products.resources;

import br.com.four.ecom.core.domains.products.models.ProductModel;

public interface FindProduct {
    ProductModel execute(Long id);
}
