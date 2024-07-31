package business.hub.accountinfoservice.controller;

import business.hub.accountinfoservice.dto.AccountDTO;
import business.hub.accountinfoservice.service.AccountInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;

/**
 * RestController Класс.
 * <p>
 * Этот класс предоставляет API для получения информации об аккаунте,
 * такой как электронная почта, на основе ID аккаунта.
 * </p>
 *
 * <p>
 * Пример запроса:
 * <pre>{@code
 * GET /api/inner/accounts/email?accountId={accountId}
 * }</pre>
 * </p>
 *
 * <p>
 * Коды состояния ответа:
 * <ul>
 *     <li>{@code 200 OK} - Успешное извлечение электронной почты</li>
 *     <li>{@code 404 NOT_FOUND} - Аккаунт с предоставленным ID не найден</li>
 * </ul>
 * </p>
 *
 * @version 1.0
 * @since 2024-04-23
 */
@RestController
@Tag(name = "main_methods_AccountInfo", description = "This class provides an API to retrieve account information")
@RequiredArgsConstructor
@RequestMapping("/api/inner/accounts")
@Slf4j
public class AccountInfoController {

    /**
     * Service, предоставляющий бизнес-логику для операций с аккаунтами.
     */
    private final AccountInfoService accountInfoService;

    /**
     * Возвращает электронную почту, на основе ID аккаунта.
     *
     * @param accountId ID аккаунта, для которого нужно извлечь электронную почту.
     * @return {@link ResponseEntity}, содержащий электронную почту и соответствующий
     * HTTP статус.
     */
    @Operation(
            summary = "based on the account ID, returns its email",
            description = "the method takes in the parameter the account ID for which you want to receive " +
                    "an email and returns the email value and HTTP status"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved email",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content)
    })
    @GetMapping("/email")
    public ResponseEntity<String> getEmailByAccountId(
            @Parameter(description = "ID of the account to retrieve email for", required = true, in = ParameterIn.QUERY)
            final @RequestParam("accountId") String accountId) {
        try {
            String email = accountInfoService.getEmailByAccountId(accountId);
            return new ResponseEntity<>(email, HttpStatus.OK);
        } catch (AccountNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    /**
     * Создает новый аккаунт на основе предоставленных данных.
     *
     * @param accountDTO объект {@link AccountDTO}, содержащий информацию о новом аккаунте.
     * @return {@link ResponseEntity}, содержащий текстовое сообщение об успешном создании аккаунта и
     * соответствующий HTTP статус.
     */
    @Operation(
            summary = "Creates a new account based on the provided data",
            description = "Accepts a {@link AccountDTO} object in the accountDTO parameters, containing information" +
                    " about the new account. Returns a ResponseEntity containing a text message indicating successful" +
                    " account creation and the corresponding HTTP status."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Account created successfully",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<String> createAccount(
            @Parameter(description = "Information about the new account", required = true)
            final @RequestBody AccountDTO accountDTO) {
        log.info("Creating account: {}", accountDTO);
        accountInfoService.createAccount(accountDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Account created successfully");
    }
    /**
     * Возвращает информацию об аккаунте на основе его ID.
     *
     * @param accountId ID аккаунта, информацию о котором необходимо получить.
     * @return {@link ResponseEntity}, содержащий объект {@link AccountDTO} с информацией об аккаунте и
     * соответствующий HTTP статус.
     * @throws AccountNotFoundException если аккаунт с предоставленным ID не найден.
     */
    @Operation(
            summary = "getting an account by ID",
            description = "accepts the parameter accountId ID of the account about which information needs" +
                    " to be obtained.Returns a ResponseEntity containing an AccountDTO object with account information" +
                    " and the corresponding HTTP status. throws AccountNotFoundException if the account " +
                    "with the provided ID is not found."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account found successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AccountDTO.class))),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content)
    })
    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> getAccount(
            @Parameter(description = "ID of the account to retrieve", required = true)
            final @PathVariable(name = "accountId") Long accountId)
            throws AccountNotFoundException {
        log.info("Fetching account with ID: {}", accountId);
        AccountDTO accountDTO = accountInfoService.getAccountByAccountId(accountId);
        return ResponseEntity.ok(accountDTO);
    }
    /**
     * Обновляет информацию об аккаунте на основе предоставленных данных и ID аккаунта.
     *
     * @param accountDTO объект {@link AccountDTO}, содержащий обновленную информацию об аккаунте.
     * @param accountId  ID аккаунта, информацию о котором необходимо обновить.
     * @return {@link ResponseEntity}, содержащий текстовое сообщение об успешном обновлении информации об аккаунте и
     * соответствующий HTTP статус.
     * @throws AccountNotFoundException если аккаунт с предоставленным ID не найден.
     */
    @Operation(
            summary = "Updates account information based on the provided data and account ID.",
            description = "Takes in the accountDTO parameters an object containing updated information about " +
                    "the account and accountId ID of the account whose information needs to be updated.\n" +
                    "Returns a ResponseEntity containing a text message indicating the successful update of account" +
                    " information and the corresponding HTTP status.\n" +
                    "throws AccountNotFoundException if the account with the provided ID is not found."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Account updated successfully",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "404", description = "Account not found", content = @Content)
    })
    @PutMapping("/{accountId}")
    public ResponseEntity<String> updateAccount(
            @Parameter(description = "Updated account information", required = true)
            final @RequestBody AccountDTO accountDTO,
            @Parameter(description = "ID of the account to update", required = true)
            final @PathVariable(name = "accountId") Long accountId)
            throws AccountNotFoundException {
        log.info("Updating account: {}", accountDTO);
        accountInfoService.updateAccount(accountDTO, accountId);
        return ResponseEntity.ok("Account updated successfully");
    }
    /**
     * Удаляет аккаунт на основе его ID.
     *
     * @param accountId ID аккаунта, который необходимо удалить.
     * @return {@link ResponseEntity}, содержащий текстовое сообщение об успешном удалении аккаунта и
     * соответствующий HTTP статус.
     * @throws AccountNotFoundException если аккаунт с предоставленным ID не найден.
     */

    @Operation(
            summary = "Deletes an account based on its ID.",
            description = "Takes accountId parameters as the ID of the account that needs to be deleted.\n" +
                    "Returns a ResponseEntity containing a text message indicating successful account deletion " +
                    "and the corresponding HTTP status.\n" +
                    "throws AccountNotFoundException if the account with the provided ID is not found."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Account deleted successfully",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
            @ApiResponse(responseCode = "404", description = "Account not found",
                    content = @Content(mediaType = "application/json"))
    })
    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccount(
            @Parameter(description = "ID of the account to delete", required = true)
            final @PathVariable(name = "accountId") Long accountId)
            throws AccountNotFoundException {
        log.info("Deleting account with ID: {}", accountId);
        accountInfoService.deleteAccount(accountId);
        return ResponseEntity.ok("Account deleted successfully");
    }
}
