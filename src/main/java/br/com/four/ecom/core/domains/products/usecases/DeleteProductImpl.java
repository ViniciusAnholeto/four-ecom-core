package br.com.four.ecom.core.domains.products.usecases;

import br.com.four.ecom.core.domains.products.exceptions.Exceptions.ProductNotFoundException;
import br.com.four.ecom.core.domains.products.models.ProductModel;
import br.com.four.ecom.core.domains.products.ports.DatabasePort;
import br.com.four.ecom.core.domains.products.resources.DeleteProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteProductImpl implements DeleteProduct {

    private final DatabasePort databasePort;

    @Override
    public void execute(String id) {
        Optional<ProductModel> product = databasePort.findProductById(id);

        if (product.isPresent()) {
            databasePort.deleteProduct(id);
        } else {
            throw new ProductNotFoundException(id);
        }
    }
}
