EurekaServerApplication.

EurekaServer - это платформа для поиска сервисов, разработанная Netflix и широко применяемая в экосистеме микросервисов Spring.
Она предоставляет серверный компонент для регистрации служб и клиентский компонент для обнаружения служб,
позволяя микросервисам самостоятельно регистрироваться и динамически находить другие службы.

## Конфигурации

Переменные окружения, необходимые для работы сервиса:

- `SwaggerConfig`: Конфигурационный класс для настройки Swagger. (Использует спецификацию OpenAPI)
- `Application name`: eureka-server
- `HOSTNAME`: `localhost`
- `SERVER_PORT`: `8761`
- `ServiceUrl`: http://localhost:8761/eureka/