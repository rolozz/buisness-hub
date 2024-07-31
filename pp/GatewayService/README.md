# GatewayService

GatewayService - это микросервис на Java, который обеспечивает шлюз для других микросервисов. Этот проект использует Spring Boot и включает документацию API с помощью Swagger (Springdoc OpenAPI).

## Требования

- Java 17 или выше
- Gradle 7.5 или выше

## Установка и запуск

1. **Клонируйте репозиторий:**

    ```bash
    git clone https://github.com/your-repository/gatewayservice.git
    cd gatewayservice
    ```

2. **Соберите проект и установите зависимости:**

    ```bash
    ./gradlew build
    ```

3. **Запустите приложение:**

    ```bash
    ./gradlew bootRun
    ```

4. **Откройте браузер и перейдите к Swagger UI для просмотра документации API:**

    ```
    http://localhost:8080/swagger-ui.html
    или
    http://localhost:8080/swagger-ui/index.html
    ```

## Структура проекта

- **configuration**: Пакет содержит классы конфигурации, включая настройки Swagger.
- **dto**: Пакет содержит классы Data Transfer Object (DTO).
- **filters**: Пакет содержит фильтры для обработки запросов.
- **jwt**: Пакет содержит классы для работы с JSON Web Token (JWT).
- **utill**: Пакет содержит утилитарные классы.

## Конфигурация Swagger

Swagger конфигурация находится в классе `SwaggerConfig` в пакете `configuration`.
