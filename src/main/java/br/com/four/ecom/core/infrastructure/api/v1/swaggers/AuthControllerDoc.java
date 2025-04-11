package br.com.four.ecom.core.infrastructure.api.v1.swaggers;

import br.com.four.ecom.core.infrastructure.api.v1.request.UserDataRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Autenticação")
public interface AuthControllerDoc {

    @Operation(summary = "Criar toeken de autenticação",
            description = "Endpoint para criar um token de autenticação para o usuário."
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
    String auth(UserDataRequest userDataRequest);
}
