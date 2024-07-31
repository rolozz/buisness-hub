package business.hub.cartservice.model;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.boot.model.source.spi.IdentifierSource;
import org.springframework.beans.factory.annotation.Value;

/**
 * Класс представляет объект продукта в корзине покупателя.
 * Каждый элемент корзины содержит информацию о продукте, его количестве и принадлежности к определенной корзине.
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "cart_product")
@Schema(description = "Сущность представляет объект продукта в корзине покупателя")
public class CartProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "id продукта корзины")
    private int id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @Schema(description="Корзина ID")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @Schema(description = "Продукт")
    private Product product;

    @Column(name = "product_quantity")
    @Schema(description = "Количество")
    private int quantity = 0;

    @Column(name = "comment")
    @Schema(description = "Комментарий к продукту")
    private String comment = "Комментарий к заказу пуст.";
}


