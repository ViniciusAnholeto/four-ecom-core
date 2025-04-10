package br.com.four.ecom.core.infrastructure.api.v1.swaggers;

import br.com.four.ecom.core.infrastructure.api.v1.request.OrderRequest;
import br.com.four.ecom.core.infrastructure.api.v1.request.PaymentRequest;
import br.com.four.ecom.core.infrastructure.api.v1.response.OrderResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "Pedidos")
public interface OrdersControllerDoc {

    @Operation(summary = "Criar ou atualizar pedido",
            description = "Endpoint para criar ou atualizar um pedido no sistema de ecommerce."
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
    OrderResponse createOrUpdateOrder(OrderRequest order);

    @Operation(summary = "Buscar pedido",
            description = "Endpoint para buscar um pedido no sistema de ecommerce."
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
    OrderResponse getOrder(String id);

    @Operation(summary = "Cancelar pedido",
            description = "Endpoint para cancelar um pedido no sistema de ecommerce."
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
    void cancelOrder(String id);

    @Operation(summary = "Buscar pedido por cliente",
            description = "Endpoint para buscar um pedido por cliente no sistema de ecommerce."
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
    List<OrderResponse> getOrderByCustomerId(String customerId);

    @Operation(summary = "Pagar pedido",
            description = "Endpoint para pagar um pedido no sistema de ecommerce."
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
    OrderResponse paymentOrder(PaymentRequest paymentRequest);
}
