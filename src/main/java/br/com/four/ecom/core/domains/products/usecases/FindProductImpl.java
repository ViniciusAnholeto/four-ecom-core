package br.com.four.ecom.core.domains.products.usecases;

import br.com.four.ecom.core.domains.products.exceptions.Exceptions.ProductNotFoundException;
import br.com.four.ecom.core.domains.products.inputs.ProductSearchInput;
import br.com.four.ecom.core.domains.products.models.ProductModel;
import br.com.four.ecom.core.domains.products.ports.DatabasePort;
import br.com.four.ecom.core.domains.products.resources.FindProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindProductImpl implements FindProduct {

    private final DatabasePort databasePort;

    @Override
    public List<ProductModel> execute(ProductSearchInput input) {
        List<ProductModel> products = databasePort.findProductByFilters(input)
                .orElseThrow(() -> new ProductNotFoundException(input));

        if (products.isEmpty()) {
            throw new ProductNotFoundException(input);
        }

        return products;
    }
}
