package business.hub.accountinfoservice.dto;

import business.hub.accountinfoservice.model.Passport;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * Объект передачи данных (DTO) для хранения информации об аккаунте.
 * Содержит электронную почту аккаунта и информацию о паспорте.
 */

@Schema(name = "AccountDTO", description = "AccountDTO a Data Transfer Object for storing account information.\n" +
        "Contains account email and passport information.")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountDTO {

    String email;

    Passport passport;
}
