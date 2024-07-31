package business.hub.cartservice.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * Конфигурационный класс для настройки Swagger.
 */

@OpenAPIDefinition(info=@io.swagger.v3.oas.annotations.info.Info(title = "CartService", version = "1.0.0",
        description = "CartService - карзина пользователя, в котором пользователь хранит свои продукты и может " +
                "совершать CRUD операции над ним."),
        security = @SecurityRequirement(name = "JWT"))
@SecurityScheme(
        name = "JWT",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)

public class SwaggerConfig {
    /**
     * Бин для конфигурации OpenAPI.
     *
     * @return объект OpenAPI с информацией о документации
     */
}
