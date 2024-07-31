package business.hub.ticketingservice.controller;

import business.hub.ticketingservice.facade.FindingTicketsFacade;
import business.hub.ticketingservice.model.Ticket;
import business.hub.ticketingservice.service.TicketingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;


import java.time.LocalDateTime;
import java.util.List;

/**
 * Контроллер для управления тикетами.
 * Реализует создание, получение, обновление и удаление тикетов.
 */
@RestController
@Tag(name = "Main methods")
@RequiredArgsConstructor
@RequestMapping("/api/tickets")
public class TicketingController {

    private final TicketingService ticketingService;
    private final FindingTicketsFacade findingTicketsFacade;

    /**
     * Создание нового тикета.
     *
     * @param ticket Тикет для создания.
     * @return Ответ с созданным тикетом и статусом HttpStatus.CREATED.
     */
    @PostMapping
    @Operation(
            summary = "Создание нового тикета",
            description = "Возвращает созданный тикет с Id"
    )
    public ResponseEntity<Ticket> createTicket(final @RequestBody Ticket ticket) {
        return new ResponseEntity<>(ticketingService.createTicket(ticket), HttpStatus.CREATED);
    }

    /**
     * Получение всех существующих тикетов.
     *
     * @return Ответ с листом тикетов и статусом HttpStatus.OK.
     */
    @GetMapping
    @Operation(
            summary = "Получение всех существующих тикетов",
            description = "Возвращает лист тикетов"
    )
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return new ResponseEntity<>(ticketingService.getAllTickets(), HttpStatus.OK);
    }

    @GetMapping("/filter")
    @Operation(
            summary = "Поиск тикетов по адресу, дате или периоду дат",
            description = "Возвращает пагинационный список тикетов, соответствующих переданным параметрам"
    )
    @ApiResponse(
            responseCode = "200", description = "The filtered list of tickets was received successfully"
    )
    @ApiResponse(
            responseCode = "204", description = "The page is empty or there are no tickets with the passed parameters"
    )
    public ResponseEntity<List<Ticket>> searchTicketsByParams(@RequestParam(required = false, value = "date1") @Parameter(description = "Date in the format yyyy-MM-dd'T'HH:mm:ss") LocalDateTime date1,
                                                              @RequestParam(required = false, value = "date2") @Parameter(description = "Date in the format yyyy-MM-dd'T'HH:mm:ss") LocalDateTime date2,
                                                              @RequestParam(required = false, value = "address") @Parameter(description = "Address") String address,
                                                              @RequestParam(defaultValue = "1", value = "pageNumber") @Parameter(description = "Number of page") int pageNumber) {
        List<Ticket> result = findingTicketsFacade.searchTicketsByParams(date1, date2, address, pageNumber);
        if (result.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Получение тикета по Id.
     *
     * @param id Идентификатор тикета.
     * @return Ответ с тикетом по указанному Id и статусом HttpStatus.OK.
     */
    @GetMapping("/{id}")
    @Operation(
            summary = "Получение тикета по Id",
            description = "Возвращает тикет по указанному Id"
    )
    public ResponseEntity<Ticket> getTicketById(final @PathVariable("id") long id) {
        return new ResponseEntity<>(ticketingService.getTicketById(id), HttpStatus.OK);
    }

    /**
     * Изменение тикета.
     *
     * @param ticket Тикет для обновления.
     * @return Ответ с обновленным тикетом и статусом HttpStatus.OK.
     */
    @PutMapping
    @Operation(
            summary = "Изменение тикета",
            description = "Возвращает изменённый тикет"
    )
    public ResponseEntity<Ticket> updateTicket(final @RequestBody Ticket ticket) {
        return new ResponseEntity<>(ticketingService.updateTicket(ticket), HttpStatus.OK);
    }

    /**
     * Удаление тикета по Id.
     *
     * @param id Идентификатор тикета для удаления.
     * @return Ответ с пустым телом и статусом HttpStatus.NO_CONTENT.
     */
    @DeleteMapping("/{id}")
    @Operation(
            summary = "Удаление тикета по Id",
            description = "Ничего не возвращает"
    )
    public ResponseEntity<Void> deleteTicket(final @PathVariable("id") long id) {
        ticketingService.deleteTicket(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

