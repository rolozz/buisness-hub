package business.hub.accountinfoservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Config-class для Swagger/OpenAPI документации.
 *
 * @version 1.0
 * @since 2024-04-23
 */
@Configuration
public class SwaggerConfig implements ApplicationListener<WebServerInitializedEvent> {
    /**
     * Бин для конфигурации OpenAPI.
     *
     * @return Экземпляр {@link OpenAPI} с указанным заголовком.
     *
     * @return Если приложение запускается с использованием динамического порта имплементим ApplicationListener.
     */

    private  int port;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.port = event.getWebServer().getPort();
    }

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .servers(
                        List.of(new Server().url("http//localhost:" + port)))
                .info(
                        new Info().title("Documentation AccountInfoServiceApplication")
                );
    }

}
