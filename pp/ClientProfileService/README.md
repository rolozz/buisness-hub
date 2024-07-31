# Client Profile Service

## Описание

Client Profile Service - это сервис для управления профилями клиентов. Этот проект 
использует Spring Boot и предоставляет REST API для операций с профилями клиентов.

## Установка

Для сборки и запуска проекта убедитесь, что у вас установлены следующие компоненты:

- JDK 17
- Gradle

## Мониторинг

Spring Boot Actuator предоставляет несколько конечных точек для мониторинга состояния приложения:

- `http://localhost:<PORT>/actuator` - выводит список всех доступных конечных точек Spring Boot Actuator.
- `http://localhost:<PORT>/actuator/health` - Показывает состояние работоспособности приложения.
- `http://localhost:<PORT>/actuator/metrics` - Отображает метрики о приложении.
- `http://localhost:<PORT>/actuator/env` - Отображает свойства из среды.
- `http://localhost:<PORT>/actuator/beans` - Показывает все компоненты Spring в контексте приложения.

Замените `<PORT>` на порт, на котором запущено приложение.
