package br.com.four.ecom.core.infrastructure.api.v1.controllers;

import br.com.four.ecom.core.domains.products.resources.CreateOrUpdateProduct;
import br.com.four.ecom.core.domains.products.resources.DeleteProduct;
import br.com.four.ecom.core.domains.products.resources.FindProduct;
import br.com.four.ecom.core.infrastructure.api.v1.request.ProductRequest;
import br.com.four.ecom.core.infrastructure.api.v1.response.ProductResponse;
import br.com.four.ecom.core.infrastructure.api.v1.swaggers.ProductsControllerDoc;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ecom/v1")
public class ProductsController implements ProductsControllerDoc {

    private CreateOrUpdateProduct createOrUpdateProduct;
    private FindProduct findProduct;
    private DeleteProduct deleteProduct;

    @Override
    @PostMapping("/product")
    public ProductResponse createOrUpdateProduct(@RequestBody @Valid ProductRequest product) {

        log.info("Product received: {}", product);

        return new ProductResponse(createOrUpdateProduct.execute(product.toInput()));
    }

    @Override
    @GetMapping("/product/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {

        log.info("getProduct: Product ID received: {}", id);

        return new ProductResponse(findProduct.execute(id));
    }

    @Override
    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable Long id) {

        log.info("deleteProduct: Product ID received: {}", id);

        deleteProduct.execute(id);
    }
}
