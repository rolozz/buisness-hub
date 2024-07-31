package business.hub.models;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

/**
 * Модельный класс продуктов.
 * @author Anatoly Zakharov
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "products")
public class Product {
    /** Поле идентификатор. */
    @Id
    @Schema(description = "Идентификационный номер продукта")
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    /** Поле наименование продукта. */
    @Schema(description = "Наименование продукта")
    @NotBlank(message = "Product name should not be empty")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я0-9\\s]+$",
            message = "Product name should contains only letters and digits")
    @Size(min = 2, max = 50, message = "Product name should be between 2 and 50 characters ")
    @Column(name = "product_name")
    private String productName;

    /** Поле цена продукта в рублях. */
    @Schema(description = "Цена продукта")
    @DecimalMin(value = "0.0")
    @Digits(integer = 10, fraction = 2,
            message = "Number is out of the acceptable range (<10 digits expected>.<2 digits>)")
    @Column(name = "product_price")
    private double productPrice;

    /** Поле описание продукта. */
    @Schema(description = "Описание продукта")
    @Size(min = 1, max = 255,
            message = "Product description should be between 1 and 255 characters ")
    @Column(name = "product_description")
    private String productDescription;

    /** Поле изготовитель продукта. */
    @Schema(description = "Производитель продукта")
    @Size(min = 2, max = 50,
            message = "Product maker should be between 2 and 50 characters ")
    @Column(name = "product_manufacturer")
    private String productManufacturer;

    /** Поле вес продукта в кг. */
    @Schema(description = "Вес продукта")
    @DecimalMin(value = "0.0")
    @Digits(integer = 4, fraction = 3,
            message = "Number is out of the acceptable range (<4 digits expected>.<3 digits>)")
    @Column(name = "product_weight")
    private double productWeight;

    /** Поле объем упаковки продукта в м3. */
    @Schema(description = "Объем продукта")
    @DecimalMin(value = "0.0")
    @Digits(integer = 2, fraction = 6,
            message = "Number is out of the acceptable range (<2 digits expected>.<6 digits>)")
    @Column(name = "product_volume")
    private double productVolume;

    /** Поле время и дата создания записи продукта в БД. */
    @Schema(description = "Время создания продукта")
    @Column(name = "product_created_at")
    private LocalDateTime createdAt;

    /** Поле время и дата изменения записи продукта в БД. */
    @Schema(description = "Время последнего изменения продукта")
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
    private double stock;

}
