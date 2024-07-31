# Spring Cloud Config Server

Spring Cloud Config Server предоставляет централизованный сервер конфигурации для микросервисов, основанных на Spring Cloud.

Чтобы нужные файлы настроек успешно находились, ваш Config Client должен быть обязательно запущен с одним из трех профилей: dev, master или test.
Способ указать, какой профиль мы хотим использовать для клиента, является окно конфигурации запуска.
![img.png](img.png)
Параметр -Dspring.profiles.active=dev говорит spring boot, что это приложение должно быть запущено именно с этим профилем.

## Мониторинг

Spring Boot Actuator предоставляет несколько конечных точек для мониторинга состояния приложения:

- `http://localhost:<PORT>/actuator` - выводит список всех доступных конечных точек Spring Boot Actuator.
- `http://localhost:<PORT>/actuator/health` - Показывает состояние работоспособности приложения.
- `http://localhost:<PORT>/actuator/metrics` - Отображает метрики о приложении.
- `http://localhost:<PORT>/actuator/env` - Отображает свойства из среды.
- `http://localhost:<PORT>/actuator/beans` - Показывает все компоненты Spring в контексте приложения.

Замените `<PORT>` на порт, на котором запущено приложение.