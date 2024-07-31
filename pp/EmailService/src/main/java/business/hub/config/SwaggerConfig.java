package business.hub.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Конфигурационный класс для настройки Swagger.
 */
@Configuration
public class SwaggerConfig implements ApplicationListener<WebServerInitializedEvent> {

    /**
     * Бин для конфигурации OpenAPI.
     *
     * @return объект OpenAPI с информацией о документации
     */

    private int port;

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.port = event.getWebServer().getPort();
    }

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .servers(
                        List.of(new Server().url("http://localhost:" + port).description("EmailService"))
                )
                .openapi("3.0.3")
                .info(
                        new Info().title("Documentation EmailService").version("0.0.1").description("Email-service отвечает за отправку сообщений электронной почты по заранее созданным шаблонам, \nпри регистрации в сервисе письмо о успешной регистрации, отправлять письмо с поздравлением в день рождения, \nписьммо успешного бронирования заказа и успешной отмены боронирования заказа.")
                )
                .tags(
                        List.of(new Tag().name("README").description("## Общие задачи\n" +
                                "\n" +
                                "- Управление аккаунтами пользователей:\n" +
                                "\n" +
                                "- Отправляет электронные письма по шаблонам.\n" +
                                "- Отправляет электронное письмо об успешном бронировании заказа.\n" +
                                "- Отправляет электронное письмо об отмене бронирования заказа.\n" +
                                "- Обрабатывает письма в соответствии шаблонами электронной почты.\n" +
                                "- Отправляет письмо поздравления в день рождения. (Не реализованно)\n" +
                                "- Отправляет письмо о успешной регистрации. (Не реализованно кроме шаблона)\n" +
                                "\n" +
                                "## Конфигурации\n" +
                                "\n" +
                                "Переменные окружения, необходимые для работы сервиса:\n" +
                                "\n" +
                                "- `StreamConfig`: Конфигурационный класс для настройки обработчика потока и проверки даты рождения.\n" +
                                "- `SwaggerConfig`: Конфигурационный класс для настройки Swagger. (Использует спецификацию OpenAPI)\n" +
                                "- `SERVER_PORT`: Порт, на котором слушает сервис (по умолчанию 0)\n" +
                                "\n" +
                                "## Email конфигурация для отправки писем через сервис\n" +
                                "\n" +
                                "- `HOST`: smtp.mail.ru\n" +
                                "- `PORT`: 587\n" +
                                "- `Login Authentication`: foma.kinyaev@internet.ru\n" +
                                "- `Password Authentication`: cMTiA1ZbZxz62jLeyFGq\n" +
                                "\n" +
                                "## Зависимости\n" +
                                "\n" +
                                "- Внешние библиотеки:\n" +
                                "- `org.springframework.boot:spring-boot-starter-actuator'\n" +
                                "- 'org.springframework.boot:spring-boot-starter'\n" +
                                "- 'org.springframework.boot:spring-boot-starter-test'\n" +
                                "- 'com.sun.mail', name: 'javax.mail', version: '1.6.2'\n" +
                                "- 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'\n" +
                                "- 'org.springframework.boot:spring-boot-starter-freemarker'\n" +
                                "- 'org.freemarker:freemarker:2.3.31'\n" +
                                "- 'org.springframework.boot:spring-boot-starter-web'\n" +
                                "- 'org.springframework.boot:spring-boot-starter-actuator'\n" +
                                "- 'org.springframework.cloud:spring-cloud-starter-netflix-eureka-client'\n" +
                                "- 'org.springframework.cloud:spring-cloud-starter-stream-kafka'\n" +
                                "- 'org.springframework.cloud:spring-cloud-stream-binder-kafka'\n" +
                                "- 'org.springframework.cloud:spring-cloud-stream-test-support'\n" +
                                "- 'org.junit.jupiter:junit-jupiter-engine:5.8.1'\n" +
                                "- 'org.postgresql:postgresql'\n" +
                                "- 'org.junit.jupiter:junit-jupiter-api:5.8.1'\n" +
                                "- 'org.springframework.boot:spring-boot-starter-test'\n" +
                                "- 'org.projectlombok:lombok:1.18.20'\n" +
                                "- 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0'\n" +
                                "\n" +
                                "### DTO\n" +
                                "\n" +
                                "- `BirthdayDTO`: Класс представления информации о дне рождения. (FirstName, LastName, Email)\n" +
                                "\n" +
                                "### Репозитории\n" +
                                "\n" +
                                "- `EmailRepository`: Репозиторий для работы с электронной почтой\n" +
                                "\n" +
                                "### Модель\n" +
                                "\n" +
                                "- `Model`: \"text/html; charset=UTF-8\"\n" +
                                "\n" +
                                "### Сервисы\n" +
                                "\n" +
                                "- `BookingCancellationEmailService`: Интерфейс для операций по отправке электронных писем об отмене бронирования\n" +
                                "- `BookingCancellationEmailServiceImpl`: Реализация `BookingCancellationEmailService`\n" +
                                "- `BookingSuccessEmailService `: Интерфейс для операций по отправке электронных писем об успешном бронировании\n" +
                                "- `BookingSuccessEmailServiceImpl`: Реализация `BookingSuccessEmailService `\n" +
                                "- `EmailService`: Интерфейс для операций по отправке электронных писем\n" +
                                "- `EmailServiceImpl`: Реализация `EmailService`\n" +
                                "- `EmailTemplateService`: Интерфейс для операций по обработке шаблонов электронной почты\n" +
                                "- `EmailTemplateServiceImpl`: Реализация `EmailTemplateService`\n" +
                                "\n" +
                                "## Форматы сообщений и событий\n" +
                                "\n" +
                                "На данный момент, сервис не использует асинхронные механизмы взаимодействия. Все взаимодействие происходит через методы.").externalDocs(new ExternalDocumentation().description("EmailService").url("EmailService/README.md")))
                );
    }
}
