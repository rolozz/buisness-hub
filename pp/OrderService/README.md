OrderService

OrderService - Микросервис управляет заказами.

## Общие задачи

Управление закзами:

- `Создает новый заказ на основе запроса.`
- `Получает заказ по его идентификатору.`
- `Получает список всех заказов для указанного пользователя.`
- `Обновляет информацию о заказе.`
- `Удаляет заказ по его идентификатору.`
- `Выполняет поиск заказов по заданным параметрам.`

## Конфигурации

Переменные окружения, необходимые для работы сервиса:

- `SwaggerConfig`: Конфигурационный класс для настройки Swagger. (Использует спецификацию OpenAPI)
- `SERVER_PORT`: Порт, на котором слушает сервис (по умолчанию 0)

## Зависимости

Внешние библиотеки:

- `org.springframework.boot:spring-boot-starter-actuator`
- `org.springframework.boot:spring-boot-starter`
- `org.springframework.boot:spring-boot-starter-test`
- `org.springframework.boot:spring-boot-starter-freemarker`
- `org.freemarker:freemarker:2.3.31`
- `org.springframework.boot:spring-boot-starter-web`
- `org.postgresql:postgresql`
- `org.springframework.boot:spring-boot-starter-test`
- `org.projectlombok:lombok`
- `org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0`

### Entity

- `Order` - Table "order_data"
- `OrderItem` - Table "orderItem"
- `OrderRequest` Entity для реквестов

### Репозитории

- `OrderRepository`: Репозиторий для работы с заказами
- `OrderItemRepository`: Репозиторий для работы с номенклатурой

### Сервисы

- `OrderService`: Сервис для операций над ордерами (добавление, удаление, изменение и получение данных)

### OrderStatus

  - `ORDER_CANCELLED`: ("Заказ отменён")
  - `ORDER_OPEN`: ("Заказ открыт")
  - `ORDER_HAD_BEEN_PAID`: ("Заказ оплачен")
  - `ORDER_DELIVERY_EXPECTED`: ("Ожидается доставка заказа")
  - `ORDER_DELIVERED`: ("Заказ доставлен")
  - `ORDER_COMPLETED_SUCCESSFULLY`: ("Заказ успешно выполнен")


    