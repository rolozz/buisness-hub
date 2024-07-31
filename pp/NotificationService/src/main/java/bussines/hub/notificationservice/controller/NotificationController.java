package bussines.hub.notificationservice.controller;

import bussines.hub.notificationservice.dto.ReservationDTO;
import bussines.hub.notificationservice.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Notification Controller", description = "API для управления уведомлениями о рейсах")
@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private NotificationService notificationService;
    private KafkaTemplate<String, ReservationDTO> kafkaTemplate;

    @Autowired
    public NotificationController(NotificationService notificationService, KafkaTemplate<String, ReservationDTO> kafkaTemplate) {
        this.notificationService = notificationService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Operation(summary = "Отправить уведомления об отмене рейса", description = "Отправляет уведомления об отмене рейса для всех резервирований с указанным номером рейса")
    @PostMapping("/cancelled/{num}")
    public void cancelledFlight(@PathVariable("num") String num) {
        List<ReservationDTO> reservList = notificationService.getFlightNumberReservations(num);
        for (ReservationDTO r : reservList) {
            kafkaTemplate.send("email-cancelled", r);
        }
    }

    @Operation(summary = "Отправить уведомления об изменении рейса", description = "Отправляет уведомления об изменении рейса для всех резервирований с указанным номером рейса")
    @PostMapping("/change/{num}")
    public void changeFlight(@PathVariable("num") String num) {
        List<ReservationDTO> reservList = notificationService.getFlightNumberReservations(num);
        for (ReservationDTO r : reservList) {
            kafkaTemplate.send("email-change", r);
        }
    }
}
