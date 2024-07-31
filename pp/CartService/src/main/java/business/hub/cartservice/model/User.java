package business.hub.cartservice.model;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * Класс представляет объект пользователя.
 * Каждый пользователь имеет id, username, cart - корзина.
 */
@Getter
@Entity
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "users")
@Schema(description = "Сущность пользователя")
public class User {
    @Schema(description = "max username length")
    @Hidden
    private static final int MAX_USER_NAME_LENGTH = 255;
    @Schema(description = "max product id length")
    @Hidden
    private static final int MAX_PRODUCT_ID_LENGTH = 255;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_users", length = MAX_PRODUCT_ID_LENGTH, nullable = false)
    @Schema(description = "id пользователя")
    private int id;

    @Column(name = "username", length = MAX_USER_NAME_LENGTH)
    @NotEmpty(message = "not NULL")
    @Schema(description = "Имя пользователя")
    private String username;

    @OneToOne
    @JoinColumn(name = "cart_id")
    @Schema(description = "Корзина")
    private Cart cart;

}
