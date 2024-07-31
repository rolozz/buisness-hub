package business.hub.cartservice.controller;

import business.hub.cartservice.model.Cart;
import business.hub.cartservice.model.CartProduct;
import business.hub.cartservice.model.Product;
import business.hub.cartservice.repositories.CartProductRepositories;
import business.hub.cartservice.repositories.ProductRepositories;
import business.hub.cartservice.repositories.UserRepositories;
import business.hub.cartservice.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.List;

/**
 * REST контроллер для работы с корзиной покупателя.
 * При переходе по "/api/cart/{id}" - вернется список продуктов
 * в корзине пользователя с указанным id.
 * При переходе по "/api/cart/{userId}/item/{itemId}" - будет
 * возможность изменить количество продукта в корзине пользователя
 * или удалить продукт из корзины.
 * /api/cart/fr - фильтр по рейтингу
 * /api/cart/fs - фильтр по акциям
 * /api/cart/fk - фильтр по размещению на складах
 */
@Tag(name="Rest колтролер для работы с карзиной",
        description="Пользователь может выполнить CRUD над корзиной")
@RestController
@RequestMapping("/api/cart")
public class CartControllerRest {

    /**
     * Сервис для работы с корзиной покупателя.
     */
    private final  CartService cartService;
    /**
     * Репозиторий для хранения связей между корзиной и продуктами.
     */
    private final  CartProductRepositories cartProductRepositories;
    /**
     * Репозиторий для хранения информации о пользователях.
     */
    private final  UserRepositories userRepositories;
    /**
     * Репозиторий для хранения информации о продуктах.
     */
    private final  ProductRepositories productRepositories;

    /**
     * Конструктор с инъекцией сервиса, репозиториев и пользователей.
     *
     * @param cartServiceParam - сервис для работы с корзиной покупателя
     * @param cartProductRepositoriesParam - репозиторий для хранения связей между корзиной и продуктами
     * @param userRepositoriesParam - репозиторий для хранения информации о пользователях
     * @param productRepositoriesparam - репозиторий для хранения информации о продуктах
     */
    @Autowired
    public CartControllerRest(final CartService cartServiceParam,
                              final CartProductRepositories cartProductRepositoriesParam,
                              final UserRepositories userRepositoriesParam,
                              final ProductRepositories productRepositoriesparam) {
        this.cartService = cartServiceParam;
        this.cartProductRepositories = cartProductRepositoriesParam;
        this.userRepositories = userRepositoriesParam;
        this.productRepositories = productRepositoriesparam;
    }

    /**
     * Получает содержимое корзины по идентификатору пользователя.
     *
     * @param id - идентификатор пользователя
     * @return список продуктов в корзине пользователя
     */

    @Operation(
            summary = "Получает корзину по id пользователя",
            description = "Получает содержимое корзины по идентификатору пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user cart"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, invalid JWT token"),
            @ApiResponse(responseCode = "403", description = "Forbidden, access denied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @SecurityRequirement(name="JWT")
    @GetMapping("/{id}")
    ResponseEntity<List<CartProduct>> getCart(
            final @PathVariable @Parameter(description = "Идентификатор пользователя ") int id) {
        return ResponseEntity.ok(cartService.getProductById(id));
    }

    /**
     * Получает отфильтрованные продукты по коэффицменту скидки.
     *
     * @param filter - параметр фильтрации
     * @return список продуктов равных значению фильтра
     */

    @Operation(
            summary = "Фильтрует продукты по значению",
            description = "Фильтрует продукты по значению скидки 0-1"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user cart"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, invalid JWT token"),
            @ApiResponse(responseCode = "403", description = "Forbidden, access denied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @SecurityRequirement(name="JWT")
    @GetMapping("/fs")
    public Iterable<Product> findAllByStock(@RequestParam(name = "filter", required = false) double filter) {
        return this.productRepositories.findAllByStock(filter);
    }

    /**
     * Получает отфильтрованные продукты по рейтингу.
     *
     * @param filter - параметр фильтрации
     * @return список продуктов равных значению фильтра
     */

    @Operation(
            summary = "Фильтрует продукты по значению",
            description = "Фильтрует продукты по значению рейтинга 0-10"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user cart"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, invalid JWT token"),
            @ApiResponse(responseCode = "403", description = "Forbidden, access denied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @SecurityRequirement(name="JWT")
    @GetMapping("/fr")
    public Iterable<Product> findAllByRating(@RequestParam(name = "filter", required = false) long filter) {
        return this.productRepositories.findAllByRating(filter);
    }

    /**
     * Получает отфильтрованные продукты игнорируя регистр.
     *
     * @param filter - параметр фильтрации
     * @return список продуктов равных значению фильтра без учета регистра
     */

    @Operation(
            summary = "Фильтрует продукты по значению",
            description = "Фильтрует продукты по значению Магазин/Склад"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user cart"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, invalid JWT token"),
            @ApiResponse(responseCode = "403", description = "Forbidden, access denied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })

    @SecurityRequirement(name="JWT")
    @GetMapping("/fk")
    public Iterable<Product> findAllByKeepingLikeIgnoreCase(@RequestParam(name = "filter", required = false) String filter) {
        return this.productRepositories.findAllByKeepingLikeIgnoreCase(filter);
    }

    /**
     * Добавляет продукт в корзину пользователя.
     *
     * @param cartProduct - продукт для добавления в корзину
     * @return продукт в корзине пользователя
     */


    @Operation(
            summary = "Добавлет продукт в карзину",
            description = "Добавляет продукт в корзину пользователя."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The cart has been successfully added"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, invalid JWT token"),
            @ApiResponse(responseCode = "403", description = "Forbidden, access denied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @SecurityRequirement(name="JWT")
    @PostMapping("/")
    ResponseEntity<CartProduct> postCart(
            final @RequestBody
            @Parameter(description = "Продукт для добавления в корзину") CartProduct cartProduct) {
        return ResponseEntity.ok(cartService.postUserCartProduct(cartProduct));
    }

    /**
     * Изменяет количество продукта в корзине пользователя.
     *
     * @param userId - идентификатор пользователя
     * @param itemId - идентификатор продукта
     * @param quantity - новое количество продукта
     * @return продукт в корзине пользователя с обновленным количеством
     */


    @Operation(
            summary = "Изменяет количество продукта в корзине",
            description = "Изменяет количество продукта в корзине пользователя."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The amount of products in cart has been " +
                    "successfully changes"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, invalid JWT token"),
            @ApiResponse(responseCode = "403", description = "Forbidden, access denied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @SecurityRequirement(name="JWT")
    @PutMapping("/{userId}/item/{itemId}")
    ResponseEntity<CartProduct> editCount(final @PathVariable("userId")
                                          @Parameter(description = "идентификатор пользователя") int userId,
                                          final @PathVariable("itemId")
                                          @Parameter(description = "идентификатор продукта") int itemId,
                                          final @RequestParam("quantity")
                                          @Parameter(description = "новое количество продукта") int quantity) {
        return ResponseEntity.ok(cartService.putCartCount(userId, itemId, quantity));
    }

    /**
     * Удаляет продукт из корзины пользователя.
     *
     * @param userId - идентификатор пользователя
     * @param itemId - идентификатор продукта
     * @return статус ответа: OK, если продукт успешно удален, CONFLICT,
     * если продукт не найден в корзине
     */


    @Operation(
            summary = "Удаляет продукт из корзины.",
            description = "Удаляет продукт из корзины пользователя."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The product has been successfully removed"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, invalid JWT token"),
            @ApiResponse(responseCode = "403", description = "Forbidden, access denied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @SecurityRequirement(name="JWT")
    @DeleteMapping("/{userId}/item/{itemId}")
    ResponseEntity<HttpStatus> delCartProduct(
            final @PathVariable("userId") @Parameter(description = "идентификатор пользователя") int userId,
            final @PathVariable("itemId") @Parameter(description = "идентификатор продукта") int itemId
    ) {
        try {
            cartService.delProductFromCart(userId, itemId);
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.ok(HttpStatus.CONFLICT);
        }
    }




    /*
    * A method to get all carts for testing purposes
    * */
    @Operation(
            summary = "Returns all carts.",
            description = "Returns all carts as a list."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The carts are successfully fetched"),
            @ApiResponse(responseCode = "401", description = "Unauthorized, invalid JWT token"),
            @ApiResponse(responseCode = "403", description = "Forbidden, access denied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @SecurityRequirement(name="JWT")
    @GetMapping
    public ResponseEntity<List<Cart>> getAllCarts(){
        return ResponseEntity.ok(cartService.getAllCarts());
    }
}
