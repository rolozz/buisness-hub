Email-service

Email-service отвечает за отправку сообщений электронной почты по заранее созданным шаблонам, 
при регистрации в сервисе письмо о успешной регистрации, отправлять письмо с поздравлением в день рождения,
письммо успешного бронирования заказа и успешной отмены боронирования заказа.

## Общие задачи

Управление аккаунтами пользователей:

- `Отправляет электронные письма по шаблонам.`
- `Отправляет электронное письмо об успешном бронировании заказа.`
- `Отправляет электронное письмо об отмене бронирования заказа.`
- `Обрабатывает письма в соответствии шаблонами электронной почты.`
- `Отправляет письмо поздравления в день рождения. (Не реализованно)`
- `Отправляет письмо о успешной регистрации. (Не реализованно кроме шаблона)`

## Конфигурации

Переменные окружения, необходимые для работы сервиса:

- `StreamConfig`: Конфигурационный класс для настройки обработчика потока и проверки даты рождения.
- `SwaggerConfig`: Конфигурационный класс для настройки Swagger. (Использует спецификацию OpenAPI)
- `SERVER_PORT`: Порт, на котором слушает сервис (по умолчанию 0)

## Email конфигурация для отправки писем через сервис

- `HOST`: smtp.mail.ru
- `PORT`: 587
- `Login Authentication`: foma.kinyaev@internet.ru
- `Password Authentication`: cMTiA1ZbZxz62jLeyFGq

## Зависимости

Внешние библиотеки:

- `org.springframework.boot:spring-boot-starter-actuator`
- `org.springframework.boot:spring-boot-starter`
- `org.springframework.boot:spring-boot-starter-test`
- `com.sun.mail', name: 'javax.mail', version: '1.6.2`
- `org.springframework.cloud:spring-cloud-starter-netflix-eureka-client`
- `org.springframework.boot:spring-boot-starter-freemarker`
- `org.freemarker:freemarker:2.3.31`
- `org.springframework.boot:spring-boot-starter-web`
- `org.springframework.boot:spring-boot-starter-actuator`
- `org.springframework.cloud:spring-cloud-starter-netflix-eureka-client`
- `org.springframework.cloud:spring-cloud-starter-stream-kafka`
- `org.springframework.cloud:spring-cloud-stream-binder-kafka`
- `org.springframework.cloud:spring-cloud-stream-test-support`
- `org.junit.jupiter:junit-jupiter-engine:5.8.1`
- `org.postgresql:postgresql`
- `org.junit.jupiter:junit-jupiter-api:5.8.1`
- `org.springframework.boot:spring-boot-starter-test`
- `org.projectlombok:lombok:1.18.20`
- `org.springdoc:springdoc-openapi-starter-webmvc-ui:2.1.0`

### DTO

- `BirthdayDTO`: Класс представления информации о дне рождения. (FirstName, LastName, Email)

### Репозитории

- `EmailRepository`: Репозиторий для работы с электронной почтой

### Модель

- `Model`: "text/html; charset=UTF-8"

### Сервисы

- `BookingCancellationEmailService`: Интерфейс для операций по отправке электронных писем об отмене бронирования
- `BookingCancellationEmailServiceImpl`: Реализация `BookingCancellationEmailService`
- `BookingSuccessEmailService `: Интерфейс для операций по отправке электронных писем об успешном бронировании
- `BookingSuccessEmailServiceImpl`: Реализация `BookingSuccessEmailService `
- `EmailService`: Интерфейс для операций по отправке электронных писем
- `EmailServiceImpl`: Реализация `EmailService`
- `EmailTemplateService`: Интерфейс для операций по обработке шаблонов электронной почты
- `EmailTemplateServiceImpl`: Реализация `EmailTemplateService`

## Форматы сообщений и событий

На данный момент, сервис не использует асинхронные механизмы взаимодействия. Все взаимодействие происходит через методы.

    