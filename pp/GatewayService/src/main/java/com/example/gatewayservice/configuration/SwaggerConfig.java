package com.example.gatewayservice.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Конфигурационный класс для настройки Swagger.
 */
@Configuration
public class SwaggerConfig {
    /**
     * Бин для конфигурации OpenAPI.
     *
     * @return объект OpenAPI с информацией о документации
     */
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info().title("Gateway Service API Documentation")
                        .description("API documentation for Gateway Service")
                        .version("1.0.0")).tags(
                        List.of(new Tag().name("README").description("GatewayServiceApplication.\n" +
                                "\n" +
                                "## Конфигурации\n" +
                                "\n" +
                                "Переменные окружения, необходимые для работы сервиса:\n" +
                                "\n" +
                                "- `SwaggerConfig`: Конфигурационный класс для настройки Swagger. (Использует спецификацию OpenAPI)\n" +
                                "- `Application name`: Gateway Service\n" +
                                "- `HOSTNAME`: `localhost`\n" +
                                "- `SERVER_PORT`: `8181`\n" +
                                "- `ServiceUrl`: http://localhost:8181/gateway/").externalDocs(new ExternalDocumentation().description("Gateway Service").url("GatewayService/README.md")))
                );
    }
}
