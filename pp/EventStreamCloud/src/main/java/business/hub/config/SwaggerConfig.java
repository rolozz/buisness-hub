package business.hub.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;

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
                        List.of(new Server().url("http://localhost:" + port).description("EventStreamCloud"))
                )
                .openapi("3.0.3")
                .info(
                        new Info().title("Documentation EventStreamCloud").version("0.0.1").description("EventStreamCloud отвечает за чтение потока Kafka из раздела \"dto-topic\", среда Serde сериализует и десериализации потоки байтов в string и json,\n" +
                                "фильтрует поток путем поиска сообщений json с данными, определенными в фильтре, конвертирует данные в DtoCreateEvent, проверяет уникальность и\n" +
                                "сохраняет ProfileEventEntity в базу данных")
                )
                .components(new Components()).schema("DTO", new Schema<>().type("object"))
                .tags(
                        List.of(new Tag().name("README").description("## Общие задачи\n" +
                                "\n" +
                                "Чтение потока создание и сохранение Entity в БД:\n" +
                                "\n" +
                                "- `Создание стрима для потока`\n" +
                                "- `Чтение потока Kafka`\n" +
                                "- `Сериализует и десериализует потоки байтов в string и json`\n" +
                                "- `Фильтрует данные проверяет на уникальность`\n" +
                                "- `Сохраняет Entity в БД`\n" +
                                "\n" +
                                "## Конфигурации\n" +
                                "\n" +
                                "База данных:\n" +
                                "\n" +
                                "- `Сontainer_name`: pg_db_eventStreamCloud\n" +
                                "- `POSTGRES_USER`: postgres\n" +
                                "- `POSTGRES_PASSWORD`: 2810\n" +
                                "- `POSTGRES_DB`: eventStreamCloud\n" +
                                "- `ports`: \"5443:5432\"\n" +
                                "\n" +
                                "Kafka:\n" +
                                "\n" +
                                "- `SPRING_KAFKA_BOOTSTRAP-SERVERS`: kafka:9092\n" +
                                "- `SPRING_KAFKA_STREAMS_BOOTSTRAP-SERVERS`: kafka:9092\n" +
                                "- `ports`:\"9092:9092\"\n" +
                                "\n" +
                                "- `KAFKA_ZOOKEEPER_CONNECT`: zookeeper:2181\n" +
                                "- `ports`: \"2181:2181\"\n" +
                                "- `ZOOKEEPER_CLIENT_PORT`: 2181\n" +
                                "\n" +
                                "\n" +
                                "## Зависимости\n" +
                                "\n" +
                                "Внешние библиотеки:\n" +
                                "\n" +
                                "- `org.springframework.boot' version '3.2.0'`\n" +
                                "- `io.spring.dependency-management' version '1.1.4'`\n" +
                                "- `org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:4.1.0'`\n" +
                                "- `org.liquibase:liquibase-core'`\n" +
                                "\n" +
                                "Spring Boot Starters:\n" +
                                "- `org.springframework.boot:spring-boot-starter-data-jpa`\n" +
                                "- `org.springframework.boot:spring-boot-starter-web`\n" +
                                "\n" +
                                "Kafka:\n" +
                                "- `org.springframework.kafka:spring-kafka`\n" +
                                "- `org.apache.kafka:kafka-streams`\n" +
                                "\n" +
                                "JSON Processing:\n" +
                                "- `com.fasterxml.jackson.core:jackson-databind`\n" +
                                "\n" +
                                "Lombok:\n" +
                                "- `org.projectlombok:lombok`\n" +
                                "- `org.projectlombok:lombok`\n" +
                                "\n" +
                                "PostgreSQL:\n" +
                                "- `org.postgresql:postgresql`\n" +
                                "\n" +
                                "SpringDoc:\n" +
                                "- `org.springdoc:springdoc-openapi-starter-webmvc-ui:${springDocVersion}`\n" +
                                "\n" +
                                "Development tools:\n" +
                                "- `org.springframework.boot:spring-boot-devtools`\n" +
                                "\n" +
                                "Testing:\n" +
                                "- `org.springframework.boot:spring-boot-starter-test`\n" +
                                "- `org.springframework.kafka:spring-kafka-test`\n" +
                                "- `org.apache.kafka:kafka-streams-test-utils`\n" +
                                "- `org.testcontainers:junit-jupiter`\n" +
                                "- `org.testcontainers:testcontainers`\n" +
                                "- `org.testcontainers:kafka`\n" +
                                "- `org.testcontainers:postgresql`\n" +
                                "\n" +
                                "### DTO\n" +
                                "\n" +
                                "- `DtoCreateEvent`: Объект DataTransferObject из Kafka dto-topic для создания объекта ProfileEventEntity\n" +
                                "\n" +
                                "### ENTITY\n" +
                                "\n" +
                                "- `ProfileEvent`: Сущность профиля c индивидуальным идентификатором UUID\n" +
                                "\n" +
                                "### Репозитории\n" +
                                "\n" +
                                "- `ProfileRepository`: Репозиторий для работы с профилями\n" +
                                "\n" +
                                "### Сервисы\n" +
                                "\n" +
                                "- `ProfileEventCreatingServices`: Сервис для создания и сохранения EntityProfile в БД\n" +
                                "\n" +
                                "- `StreamProcess`: Сервис для конвертации JSON в специфические значения для DTO\n" +
                                "\n" +
                                "- `StreamInit`: Utils класс для создания потока \"dto-topic\" (KStream)\n" +
                                "\n" +
                                "## Форматы сообщений и событий\n" +
                                "\n" +
                                "Использует асинхронные механизмы потоков обмена сообщениями Kafka. Логирование Logger формата Info").externalDocs(new ExternalDocumentation().description("EventStreamCloud").url("EventStreamCloud/README.md")))
                );
    }
}