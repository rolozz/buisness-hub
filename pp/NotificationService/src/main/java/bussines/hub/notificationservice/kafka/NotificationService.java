package bussines.hub.notificationservice.kafka;

import bussines.hub.notificationservice.dto.TicketDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    /**
     * Обработчик событий создания тикетов
     *
     * @param ticket  DTO тикета
     */
    @KafkaListener(topics = "ticket-events-create", groupId = "notificationGroup")
    public void listenTicketEventsCreate(TicketDTO ticket) {
    }

    /**
     * Обработчик событий изменения статуса тикетов
     *
     * @param ticket  DTO тикета
     */
    @KafkaListener(topics = "ticket-events-update-status", groupId = "notificationGroup")
    public void listenTicketEventsUpdateStatus(TicketDTO ticket) {
    }


}

