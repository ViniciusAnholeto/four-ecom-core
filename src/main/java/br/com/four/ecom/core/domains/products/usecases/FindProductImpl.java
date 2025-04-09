package br.com.four.ecom.core.domains.products.usecases;

import br.com.four.ecom.core.domains.products.exceptions.Exceptions.ProductNotFoundException;
import br.com.four.ecom.core.domains.products.models.ProductModel;
import br.com.four.ecom.core.domains.products.ports.DatabasePort;
import br.com.four.ecom.core.domains.products.resources.FindProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindProductImpl implements FindProduct {

    private final DatabasePort databasePort;

    @Override
    public ProductModel execute(String id) {
        return databasePort.findProductById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
}
