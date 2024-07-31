package bussines.hub.notificationservice.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI api() {
        return  new OpenAPI()
                .info(new Info().title("Notification Service API Documentation")
                        .description("API documentation for Notification Service")
                        .version("1.0.0")).tags(
                        List.of(new Tag().name("README").description("NotificationServiceApplication.\n" +
                                "\n" +
                                "## Конфигурации\n" +
                                "\n" +
                                "Переменные окружения, необходимые для работы сервиса:\n" +
                                "\n" +
                                "- `SwaggerConfig`: Конфигурационный класс для настройки Swagger. (Использует спецификацию OpenAPI)\n" +
                                "- `Application name`: Notification Service\n" +
                                "- `HOSTNAME`: `localhost`\n" +
                                "- `SERVER_PORT`: `0`\n" +
                                "- `ServiceUrl`: http://localhost:0/notifications/").externalDocs(new ExternalDocumentation().description("Notification Service").url("NotificationService/README.md")))
                );
    }
}