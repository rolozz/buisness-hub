API Billing-Service: Автоматическое выставление счетов и оплата картой

GET /bills/{id}: Получение информации о счете по ID.
POST /bills: Создание нового счета.
PUT /bills/{id}: Обновление счета.
DELETE /bills/{id}: Отмена счета.


http://localhost:49306/actuator - main page with all endpoints
http://localhost:49306/actuator/health - show app status (up while run)
http://localhost:59556/actuator/metrics - all metrics in app
