package br.com.four.ecom.core.domains.products.usecases;

import br.com.four.ecom.core.domains.products.models.ProductModel;
import br.com.four.ecom.core.domains.products.resources.FindProduct;

public class FindProductImpl implements FindProduct {

    @Override
    public ProductModel execute(Long id) {
        //logica para buscar produto
        return new ProductModel();
    }
}
