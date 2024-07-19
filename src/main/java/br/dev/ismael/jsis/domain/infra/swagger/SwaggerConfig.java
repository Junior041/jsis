package br.dev.ismael.jsis.domain.infra.swagger;

import br.dev.ismael.jsis.domain.infra.security.PublicRoute;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")
                        )
                )
                .security(List.of(new SecurityRequirement().addList("bearerAuth")))
                .info(new Info()
                        .title("JSIS")
                        .description("API TESTES JUNIOR")
                        .version("1")
                );
    }

    @Bean
    public OperationCustomizer customOperationCustomizer() {
        return (Operation operation, HandlerMethod handlerMethod) -> {
            if (handlerMethod.getMethod().isAnnotationPresent(PublicRoute.class) ||
                    handlerMethod.getBeanType().isAnnotationPresent(PublicRoute.class)) {
                operation.setSecurity(List.of());
            }
            return operation;
        };
    }
}