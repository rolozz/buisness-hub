package business.hub.cartservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Класс представляет объект корзины покупателя.
 * Корзина содержит информацию о её идентификаторе и общей стоимости продуктов в ней.
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "cart")
@Schema(description = "Сущность корзины")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartId")
    @Schema(description = "id корзины")
    private int id;

    @Column(name = "totalPrice")
    @Schema(description = "Итоговая цена")
    private int totalPrice;
}
