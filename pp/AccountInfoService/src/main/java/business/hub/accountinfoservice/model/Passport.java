package business.hub.accountinfoservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

/**
 * Модель Passport.
 * <p>
 * Этот класс представляет собой Entity паспорта.
 * </p>
 *d
 * <p>
 *
 * <p>
 * Имя таблицы: {@code passport}
 * </p>
 *
 */
@Schema(name = "Passport", description = "Passport entity representing the passport details of a user")
@Entity
@Table(name = "passport")
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Passport {

    @Schema(description = "Full name of the passport holder", example = "Иванов Иван Иванович")
    @Column(name = "fullName")
    String fullName;

    @Schema(description = "Date of birth of the passport holder", example = "1980-01-01T00:00:00")
    @Column(name = "dateOfBirth")
    LocalDateTime dateOfBirth;

    @Schema(description = "Passport number", example = "1409 9999999")
    @Column(name = "passportNumber")
    String passportNumber;

    @Schema(description = "Issuing authority", example = "Ивановское РОВД")
    @Column(name = "issuingAuthority")
    String issuingAuthority;

    @Schema(description = "Date of issue of the passport", example = "2000-01-01T00:00:00")
    @Column(name = "issueDate")
    LocalDateTime issueDate;

    @Schema(description = "Expiry date of the passport", example = "2025-01-01T00:00:00")
    @Column(name = "expiryDate")
    LocalDateTime expiryDate;

    @Schema(description = "Unique identifier of the passport", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passport_id")
    Long id;

    @OneToOne(mappedBy = "passport")
    Account account;

    /**
     * Конструктор класса Passport для создания экземпляра паспорта с указанными данными.
     *
     * @param fullNameParam         полное имя владельца паспорта.
     * @param dateOfBirthParam      дата рождения владельца паспорта.
     * @param passportNumberParam   номер паспорта.
     * @param issuingAuthorityParam орган, выдавший паспорт.
     * @param issueDateParam        дата выдачи паспорта.
     * @param expiryDateParam       дата истечения срока действия паспорта.
     */
    public Passport(final String fullNameParam, final LocalDateTime dateOfBirthParam,
                    final String passportNumberParam, final String issuingAuthorityParam,
                    final LocalDateTime issueDateParam, final LocalDateTime expiryDateParam) {
        this.fullName = fullNameParam;
        this.dateOfBirth = dateOfBirthParam;
        this.passportNumber = passportNumberParam;
        this.issuingAuthority = issuingAuthorityParam;
        this.issueDate = issueDateParam;
        this.expiryDate = expiryDateParam;
    }

}
