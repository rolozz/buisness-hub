package business.hub.ticketingservice.facade;

import business.hub.ticketingservice.model.Ticket;
import business.hub.ticketingservice.service.ReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Класс для реализации логики поиска тикетов.
 * Реализует логику для поиска тикетов в зависимости от переданных параметров.
 */
@Component
@RequiredArgsConstructor
public class FindingTicketsFacade {
    private final ReadService readService;
    /**
     * Поиск тикетов по переданным параметрам.
     *
     * @param date1 - левая граница периода дат.
     * @param date2 - правая граница периода дат (если этот параметр не был передан, а date1 передан,
     *              то будет происходить тикетов у которых eventDateTime соответствует date1).
     * @param address - адрес.
     * @param pageNumber - номер страницы.
     * @return List тикетов, отфильтрованный по переданным параметрам и отсортированный по полю eventDateTime в порядке возрастания.
     */
    public List<Ticket> searchTicketsByParams(LocalDateTime date1, LocalDateTime date2, String address, int pageNumber) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 10);
        if (date1 == null && date2 == null && address == null) {
            return readService.findAllOrderByEventDateTime(pageRequest); // if no parameters were passed, a list of all tickets is returned
        } else if (date1 != null) {
            if (date2 != null) {
                if (address != null) {
                    return readService.findByAddressAndEventDateTimeBetweenOrderByEventDateTime(address, date1, date2, pageRequest); // if all parameters are passed,
                    // a list of tickets filtered by address and date period is returned
                } else {
                    return readService.findByEventDateTimeBetweenOrderByEventDateTime(date1, date2, pageRequest); // if date1 and date2 are passed,
                    // a list of tickets filtered by date period is returned
                }
            }
            if (address != null) {
                return readService.findByAddressAndEventDateTimeOrderByEventDateTime(address, date1, pageRequest); // if date1 and address are passed,
                // a list of tickets filtered by date and address is returned
            }
            return readService.findByEventDateTimeOrderByEventDateTime(date1, pageRequest); // if only date1 is passed,
            // a list of tickets sorted by date is returned
        }
        return readService.findByAddressOrderByEventDateTime(address, pageRequest); // if only an address is passed,
        // a list of tickets filtered by address is returned
    }
}
