package br.com.four.ecom.core.domains.products.resources;

import br.com.four.ecom.core.domains.products.models.ProductModel;

import java.util.UUID;

public interface FindProduct {
    ProductModel execute(UUID id);
}
