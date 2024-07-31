package business.hub.ticketingservice.service;

import business.hub.ticketingservice.model.Ticket;
import business.hub.ticketingservice.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Реализация интерфейса ReadService для поиска тикетов по переданным параметрам.
 */
@Service
@RequiredArgsConstructor
public class ReadServiceImpl implements ReadService {

    private final TicketRepository ticketRepository;

    /**
     * Получение всех тикетов постранично.
     *
     * @param page - объект Pageable, который содержит информацию о номере страницы и ее вместимости.
     * @return List, содержащий все тикеты.
     */
    @Override
    public List<Ticket> findAllOrderByEventDateTime(Pageable page) {
        return ticketRepository.findAllOrderByEventDateTime(page);
    }
    /**
     * Фильтрация тикетов по адресу c постраничным выводом.
     *
     * @param address - адрес.
     * @param page - объект Pageable, который содержит информацию о номере страницы и ее вместимости.
     * @return List, содержащий тикеты, отфильтрованные по адресу.
     */
    @Override
    public List<Ticket> findByAddressOrderByEventDateTime(String address, Pageable page) {
        return ticketRepository.findByAddressOrderByEventDateTime(address, page);
    }
    /**
     * Фильтрация тикетов по дате c постраничным выводом.
     *
     * @param eventDateTime - дата.
     * @param page - объект Pageable, который содержит информацию о номере страницы и ее вместимости.
     * @return List, содержащий тикеты, отфильтрованные по дате.
     */
    @Override
    public List<Ticket> findByEventDateTimeOrderByEventDateTime(LocalDateTime eventDateTime, Pageable page) {
        return ticketRepository.findByEventDateTimeOrderByEventDateTime(eventDateTime, page);
    }
    /**
     * Фильтрация тикетов по полю периоду дат c постраничным выводом.
     *
     * @param eventDateTime1 - левая граница периода дат.
     * @param eventDateTime2 - правая граница периода дат.
     * @param page - объект Pageable, который содержит информацию о номере страницы и ее вместимости.
     * @return List, содержащий тикеты, отфильтрованные по периоду дат.
     */
    @Override
    public List<Ticket> findByEventDateTimeBetweenOrderByEventDateTime(LocalDateTime eventDateTime1, LocalDateTime eventDateTime2, Pageable page) {
        return ticketRepository.findByEventDateTimeBetweenOrderByEventDateTime(eventDateTime1, eventDateTime2, page);
    }
    /**
     * Фильтрация тикетов по адресу и дате c постраничным выводом.
     *
     * @param address - адрес.
     * @param eventDateTime - дата.
     * @param page - объект Pageable, который содержит информацию о номере страницы и ее вместимости.
     * @return List, содержащий тикеты, отфильтрованные по адресу и дате.
     */
    @Override
    public List<Ticket> findByAddressAndEventDateTimeOrderByEventDateTime(String address, LocalDateTime eventDateTime, Pageable page) {
        return ticketRepository.findByAddressAndEventDateTimeOrderByEventDateTime(address, eventDateTime, page);
    }
    /**
     * Фильтрация тикетов по адресу и периоду дат c постраничным выводом.
     *
     * @param address - адрес.
     * @param eventDateTime1 - левая граница периода дат.
     * @param eventDateTime2 - правая граница периода дат.
     * @param page - объект Pageable, который содержит информацию о номере страницы и ее вместимости.
     * @return List, содержащий тикеты, отфильтрованные по адресу и периоду дат.
     */
    @Override
    public List<Ticket> findByAddressAndEventDateTimeBetweenOrderByEventDateTime(String address, LocalDateTime eventDateTime1, LocalDateTime eventDateTime2, Pageable page) {
        return ticketRepository.findByAddressAndEventDateTimeBetweenOrderByEventDateTime(address, eventDateTime1, eventDateTime2, page);
    }
}
