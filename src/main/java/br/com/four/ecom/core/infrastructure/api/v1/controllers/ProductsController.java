package br.com.four.ecom.core.infrastructure.api.v1.controllers;

import br.com.four.ecom.core.domains.products.resources.CreateOrUpdateProduct;
import br.com.four.ecom.core.domains.products.resources.DeleteProduct;
import br.com.four.ecom.core.domains.products.resources.FindProduct;
import br.com.four.ecom.core.infrastructure.api.v1.request.ProductRequest;
import br.com.four.ecom.core.infrastructure.api.v1.request.ProductSearchRequest;
import br.com.four.ecom.core.infrastructure.api.v1.response.ProductResponse;
import br.com.four.ecom.core.infrastructure.api.v1.swaggers.ProductsControllerDoc;
import br.com.four.ecom.core.infrastructure.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ecom/v1")
public class ProductsController implements ProductsControllerDoc {

    private final CreateOrUpdateProduct createOrUpdateProduct;
    private final FindProduct findProduct;
    private final DeleteProduct deleteProduct;
    private final AuthService authorizationService;

    @Override
    @PostMapping("/product")
    public ProductResponse createOrUpdateProduct(@RequestHeader("Authorization") String authorizationHeader,
                                                 @RequestBody @Valid ProductRequest product) {

        String token = authorizationHeader.replace("Bearer ", "");
        authorizationService.validateRole(token, "ADMIN", "createOrUpdateProduct");

        log.info("Product received: {}", product);

        return new ProductResponse(createOrUpdateProduct.execute(product.toInput()));
    }

    @Override
    @PostMapping("/product/search")
    public List<ProductResponse> getProduct(@RequestBody ProductSearchRequest request) {

        log.info("Product search received: {} - getProduct", request);

        return ProductResponse.toList(findProduct.execute(request.toInput()));
    }

    @Override
    @DeleteMapping("/product/{id}")
    public void deleteProduct(@RequestHeader("Authorization") String authorizationHeader, @PathVariable String id) {

        String token = authorizationHeader.replace("Bearer ", "");
        authorizationService.validateRole(token, "ADMIN", "deleteProduct");

        log.info("Product ID received: {} - deleteProduct", id);

        deleteProduct.execute(id);
    }
}
