package business.hub.accountinfoservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * Модель Account.
 * <p>
 * Этот класс представляет собой Entity аккаунта.
 * </p>
 *d
 * <p>
 * Поля:
 * <ul>
 *     <li>{@code passport} - личный паспорт человека</li>
 *     <li>{@code id} - Уникальный идентификатор аккаунта</li>
 *     <li>{@code email} - Уникальная электронная почта</li>
 * </ul>
 * </p>
 *
 * <p>
 * Имя таблицы: {@code accounts}
 * </p>
 *
 * @version 1.0
 * @since 2024-04-23
 */
@Schema(name = "Account", description = "Account entity representing the account details of a user")
@Entity
@Table(name = "accounts")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {

    @Schema(description = "Passport associated with the account")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport", referencedColumnName = "passport_id")
    Passport passport;

    @Schema(description = "Unique identifier of the account", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    Long id;

    @Schema(description = "Email of the account holder", example = "user@mail.ru", required = true)
    @Column(nullable = false, unique = true, name = "email")
    String email;

}
