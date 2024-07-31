package business.hub.cartservice.model;

import business.hub.cartservice.enums.stock;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DialectOverride;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

import static business.hub.cartservice.enums.stock.*;

/**
 * Класс представляет объект продукта.
 * Каждый продукт имеет идентификатор, название, описание, цену, производителя и вес.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name = "products")
@Schema(description = "Сущность продукта")
public class Product {

    private static final int MAX_PRODUCT_ID_LENGTH = 255;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", length = MAX_PRODUCT_ID_LENGTH, nullable = false)
    @Schema(description = "id продукта")
    private int id;

    @Column(name = "product_name")
    @NotNull
    @Schema(description = "Имя")
    private String name;

    @Column(name = "product_description")
    @Schema(description = "Описнаие")
    private String description;

    @Column(name = "product_price")
    @Schema(description = "Цена")
    private double price;

    @Column(name = "product_manufacturer")
    @Schema(description = "Производитель")
    private String manufacturer;

    @Column(name = "product_weight")
    @Schema(description = "Вес")
    private double weight;

    @Column(name = "product_volume")
    @Schema(description = "Вес")
    private double productVolume;

    /** Поле время и дата создания записи продукта в БД. */
    @Column(name = "product_created_at")
    @Schema(description = "Дата создания продукта")
    private LocalDateTime createdAt;

    /** Поле время и дата изменения записи продукта в БД. */
    @Schema(description = "Дата изменения продукта")
    @Column(name = "product_updated_at")
    private LocalDateTime updatedAt;

    /** Поле остатки продукта на Магазине/Складе. */
    @Schema(description = "Локация продукта")
    @Column(name = "product_keeping")
    private String keeping;

    /** Поле рейтинг продукта от 0 до 10. */
    @Schema(description = "Рейтинг продукта")
    @Column(name = "product_rating")
    private long rating;

    /** Поле процент скидки на продукт */
    @Schema(description = "Акция на продукт")
    @Column(name = "product_stock")
    private double stock = 1;

}
