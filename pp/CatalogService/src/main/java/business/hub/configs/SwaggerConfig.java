package business.hub.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Конфигурация Swagger, которая определяет базовый URL и информацию о API.
 */
@Configuration
public class SwaggerConfig {
    /**
     * Создает и возвращает объект OpenAPI, определяя базовый URL и информацию о API.
     *
     * @return объект OpenAPI с настройками API
     */
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info().title("Catalog service")
                );
    }
}
