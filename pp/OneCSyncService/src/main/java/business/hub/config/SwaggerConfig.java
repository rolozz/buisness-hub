package business.hub.config;

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
        return  new OpenAPI()
                .info(new Info().title("One CSync Service Service API Documentation")
                        .description("API documentation for One CSync Service")
                        .version("1.0.0")).tags(
                        List.of(new Tag().name("README").description("OneCSyncService - это микросервис на Java, предназначенный для синхронизации данных между различными системами. Этот проект использует Spring Boot и включает документацию API с помощью Swagger (Springdoc OpenAPI).\n.\n" +
                                "\n" +
                                "## Конфигурации\n" +
                                "\n" +
                                "Переменные окружения, необходимые для работы сервиса:\n" +
                                "\n" +
                                "- `SwaggerConfig`: Конфигурационный класс для настройки Swagger. (Использует спецификацию OpenAPI)\n" +
                                "- `Application name`: One CSync Service\n" +
                                "- `HOSTNAME`: `localhost`\n" +
                                "- `SERVER_PORT`: `0`\n" +
                                "- `ServiceUrl`: http://localhost:0/notifications/").externalDocs(new ExternalDocumentation().description("One CSync Service").url("OneCSyncService/README.md")))
                );
    }
}
