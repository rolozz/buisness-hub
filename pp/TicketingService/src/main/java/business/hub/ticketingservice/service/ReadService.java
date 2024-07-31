package business.hub.ticketingservice.service;

import business.hub.ticketingservice.model.Ticket;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Интерфейс сервиса для поиска тикетов по переданным параметрам.
 * Определяет методы для фильтрации тикетов по адресу, дате или периоду дат.
 */
public interface ReadService {
    /**
     * Получение всех тикетов постранично.
     *
     * @param page - объект Pageable, который содержит информацию о номере страницы и ее вместимости.
     * @return List, содержащий все тикеты.
     */
    List<Ticket> findAllOrderByEventDateTime(Pageable page);
    /**
     * Фильтрация тикетов по адресу c постраничным выводом.
     *
     * @param address - адрес.
     * @param page - объект Pageable, который содержит информацию о номере страницы и ее вместимости.
     * @return List, содержащий тикеты, отфильтрованные по адресу.
     */
    List<Ticket> findByAddressOrderByEventDateTime(String address, Pageable page);
    /**
     * Фильтрация тикетов по дате c постраничным выводом.
     *
     * @param eventDateTime - дата.
     * @param page - объект Pageable, который содержит информацию о номере страницы и ее вместимости.
     * @return List, содержащий тикеты, отфильтрованные по дате.
     */
    List<Ticket> findByEventDateTimeOrderByEventDateTime(LocalDateTime eventDateTime, Pageable page);
    /**
     * Фильтрация тикетов по полю периоду дат c постраничным выводом.
     *
     * @param eventDateTime1 - левая граница периода дат.
     * @param eventDateTime2 - правая граница периода дат.
     * @param page - объект Pageable, который содержит информацию о номере страницы и ее вместимости.
     * @return List, содержащий тикеты, отфильтрованные по периоду дат.
     */
    List<Ticket> findByEventDateTimeBetweenOrderByEventDateTime(LocalDateTime eventDateTime1, LocalDateTime eventDateTime2, Pageable page);
    /**
     * Фильтрация тикетов по адресу и дате c постраничным выводом.
     *
     * @param address - адрес.
     * @param eventDateTime - дата.
     * @param page - объект Pageable, который содержит информацию о номере страницы и ее вместимости.
     * @return List, содержащий тикеты, отфильтрованные по адресу и дате.
     */
    List<Ticket> findByAddressAndEventDateTimeOrderByEventDateTime(String address, LocalDateTime eventDateTime, Pageable page);
    /**
     * Фильтрация тикетов по адресу и периоду дат c постраничным выводом.
     *
     * @param address - адрес.
     * @param eventDateTime1 - левая граница периода дат.
     * @param eventDateTime2 - правая граница периода дат.
     * @param page - объект Pageable, который содержит информацию о номере страницы и ее вместимости.
     * @return List, содержащий тикеты, отфильтрованные по адресу и периоду дат.
     */
    List<Ticket> findByAddressAndEventDateTimeBetweenOrderByEventDateTime(String address, LocalDateTime eventDateTime1, LocalDateTime eventDateTime2, Pageable page);
}
