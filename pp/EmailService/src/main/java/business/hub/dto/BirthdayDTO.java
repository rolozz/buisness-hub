package business.hub.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Класс для представления информации о дне рождения.
 */
@Data
@AllArgsConstructor
@Schema(description = "Сущность данных пользователя")
public class BirthdayDTO {

    @Schema(description = "Электронный ящик пользователя", example = "123@mail.ru")
    private String email;

    @Schema(description = "Имя пользователя", example = "Alexandr")
    private String firstName;

    @Schema(description = "Фамилия пользователя", example = "Popravka")
    private String lastName;
}
