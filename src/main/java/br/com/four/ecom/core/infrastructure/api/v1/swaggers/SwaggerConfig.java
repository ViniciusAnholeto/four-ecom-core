package br.com.four.ecom.core.infrastructure.api.v1.swaggers;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
class SwaggerConfig {

    @Bean
    public OpenAPI openApiConfiguration(
            @Value("${info.app.name}") final String title,
            @Value("${info.app.version}") final String version,
            @Value("${info.app.description}") final String description
    ) {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(HttpHeaders.AUTHORIZATION))
                .components(
                        new Components()
                                .addSecuritySchemes(HttpHeaders.AUTHORIZATION, authorization())
                )
                .info(new Info()
                        .title(title)
                        .version(version)
                        .description(description)
                );
    }

    public SecurityScheme authorization() {
        return new SecurityScheme()
                .name(HttpHeaders.AUTHORIZATION)
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER);
    }
}
