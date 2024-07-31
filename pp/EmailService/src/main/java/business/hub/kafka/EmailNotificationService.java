package business.hub.kafka;

import business.hub.dto.TicketDTO;
import business.hub.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmailNotificationService {

    private final EmailService emailService;


    /**
     * Обработчик событий создания тикетов
     *
     * @param ticket  DTO тикета
     */
    @KafkaListener(topics = "ticket-events-create", groupId = "notificationGroup")
    public void listenTicketEventsCreate(TicketDTO ticket) {

        Map<String, Object> model = new HashMap<>();

        model.put("name", "Admin");
        model.put("ticketId", ticket.getId());
        model.put("status", ticket.getStatus());
        model.put("description", ticket.getDescription());

        emailService.sendMail(
                "admin@example.ru",
                "Новый тикет",
                "ticketEventAdmin",
                model);
    }

    /**
     * Обработчик событий изменения статуса тикетов
     *
     * @param ticket  DTO тикета
     */
    @KafkaListener(topics = "ticket-events-update-status", groupId = "notificationGroup")
    public void listenTicketEventsUpdateStatus(TicketDTO ticket) {

        Map<String, Object> model = new HashMap<>();

        model.put("name", "Admin");
        model.put("ticketId", ticket.getId());
        model.put("status", ticket.getStatus());
        model.put("description", ticket.getDescription());

        emailService.sendMail(
                "admin@example.ru",
                "Обновлён статус тикета",
                "ticketEventAdmin",
                model);
    }
}
