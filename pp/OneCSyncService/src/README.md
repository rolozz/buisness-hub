OneCSyncService - это микросервис на Java, предназначенный для синхронизации данных между различными системами. Этот проект использует Spring Boot и включает документацию API с помощью Swagger (Springdoc OpenAPI).

## Требования

- Java 17 или выше
- Gradle 7.5 или выше

## Установка и запуск

1. **Клонируйте репозиторий:**

    ```bash
    git clone https://github.com/your-repository/onecsyncservice.git
    cd onecsyncservice
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
- **service**: Пакет содержит сервисные классы для обработки бизнес-логики.
- **controller**: Пакет содержит контроллеры для обработки HTTP-запросов.
- **repository**: Пакет содержит интерфейсы для взаимодействия с базой данных.
- **model**: Пакет содержит сущности и модели данных.

## Конфигурация Swagger

Swagger конфигурация находится в классе `SwaggerConfig` в пакете `configuration`.