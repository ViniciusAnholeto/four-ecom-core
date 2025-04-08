package br.com.four.ecom.core.infrastructure.api.v1.swaggers;

import br.com.four.ecom.core.infrastructure.api.v1.request.ProductRequest;
import br.com.four.ecom.core.infrastructure.api.v1.response.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.UUID;

@Tag(name = "Produtos")
public interface ProductsControllerDoc {

    @Operation(summary = "Criar produto",
            description = "Endpoint para criar um produto no sistema de ecommerce."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Em casos de sucesso na requisicao."),
            @ApiResponse(responseCode = "400", description = "Em casos de parametros errados ou faltantes.", content = @Content()),
            @ApiResponse(responseCode = "401", description = "Em casos de requisição não autorizada.", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Em casos de requisição não processada.", content = @Content()),
            @ApiResponse(responseCode = "422", description = "Em casos de erros interno nao tratados.", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Em casos de erro no processamento do ecom-core.", content = @Content())
    })
    ProductResponse createOrUpdateProduct(ProductRequest product);

    @Operation(summary = "Buscar produto",
            description = "Endpoint para buscar um produto no sistema de ecommerce."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Em casos de sucesso na requisicao."),
            @ApiResponse(responseCode = "400", description = "Em casos de parametros errados ou faltantes.", content = @Content()),
            @ApiResponse(responseCode = "401", description = "Em casos de requisição não autorizada.", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Em casos de requisição não processada.", content = @Content()),
            @ApiResponse(responseCode = "422", description = "Em casos de erros interno nao tratados.", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Em casos de erro no processamento do ecom-core.", content = @Content())
    })
    ProductResponse getProduct(UUID id);

    @Operation(summary = "Deletar produto",
            description = "Endpoint para deletar um produto no sistema de ecommerce."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Em casos de sucesso na requisicao."),
            @ApiResponse(responseCode = "400", description = "Em casos de parametros errados ou faltantes.", content = @Content()),
            @ApiResponse(responseCode = "401", description = "Em casos de requisição não autorizada.", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Em casos de requisição não processada.", content = @Content()),
            @ApiResponse(responseCode = "422", description = "Em casos de erros interno nao tratados.", content = @Content()),
            @ApiResponse(responseCode = "500", description = "Em casos de erro no processamento do ecom-core.", content = @Content())
    })
    void deleteProduct(UUID id);
}
