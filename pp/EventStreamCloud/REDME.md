EventStreamCloud

EventStreamCloud отвечает за чтение потока Kafka из раздела "dto-topic", среда Serde сериализует и десериализации потоки байтов в string и json,
фильтрует поток путем поиска сообщений json с данными, определенными в фильтре, конвертирует данные в DtoCreateEvent, проверяет уникальность и
сохраняет ProfileEventEntity в базу данных

## Общие задачи

Чтение потока создание и сохранение Entity в БД:

- `Создание стрима для потока`
- `Чтение потока Kafka`
- `Сериализует и десериализует потоки байтов в string и json`
- `Фильтрует данные проверяет на уникальность`
- `Сохраняет Entity в БД`

## Конфигурации

База данных:

- `Сontainer_name`: pg_db_eventStreamCloud
- `POSTGRES_USER`: postgres
- `POSTGRES_PASSWORD`: 2810
- `POSTGRES_DB`: eventStreamCloud
- `ports`: "5443:5432"

Kafka:

- `SPRING_KAFKA_BOOTSTRAP-SERVERS`: kafka:9092
- `SPRING_KAFKA_STREAMS_BOOTSTRAP-SERVERS`: kafka:9092
- `ports`:"9092:9092"

- `KAFKA_ZOOKEEPER_CONNECT`: zookeeper:2181
- `ports`: "2181:2181"
- `ZOOKEEPER_CLIENT_PORT`: 2181


## Зависимости

Внешние библиотеки:

- `org.springframework.boot' version '3.2.0'`
- `io.spring.dependency-management' version '1.1.4'`
- `org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:4.1.0'`
- `org.liquibase:liquibase-core'`

Spring Boot Starters:
- `org.springframework.boot:spring-boot-starter-data-jpa`
- `org.springframework.boot:spring-boot-starter-web`

Kafka:
- `org.springframework.kafka:spring-kafka`
- `org.apache.kafka:kafka-streams`

JSON Processing:
- `com.fasterxml.jackson.core:jackson-databind`

Lombok:
- `org.projectlombok:lombok`
- `org.projectlombok:lombok`

PostgreSQL:
- `org.postgresql:postgresql`

SpringDoc:
- `org.springdoc:springdoc-openapi-starter-webmvc-ui:${springDocVersion}`

Development tools:
- `org.springframework.boot:spring-boot-devtools`

Testing:
- `org.springframework.boot:spring-boot-starter-test`
- `org.springframework.kafka:spring-kafka-test`
- `org.apache.kafka:kafka-streams-test-utils`
- `org.testcontainers:junit-jupiter`
- `org.testcontainers:testcontainers`
- `org.testcontainers:kafka`
- `org.testcontainers:postgresql`

### DTO

- `DtoCreateEvent`: Объект DataTransferObject из Kafka dto-topic для создания объекта ProfileEventEntity

### ENTITY

- `ProfileEvent`: Сущность профиля c индивидуальным идентификатором UUID

### Репозитории

- `ProfileRepository`: Репозиторий для работы с профилями

### Сервисы

- `ProfileEventCreatingServices`: Сервис для создания и сохранения EntityProfile в БД

- `StreamProcess`: Сервис для конвертации JSON в специфические значения для DTO

- `StreamInit`: Utils класс для создания потока "dto-topic" (KStream)

## Форматы сообщений и событий

Использует асинхронные механизмы потоков обмена сообщениями Kafka. Логирование Logger формата Info

    