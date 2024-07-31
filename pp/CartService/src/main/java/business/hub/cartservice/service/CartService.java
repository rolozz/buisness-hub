package business.hub.cartservice.service;

import business.hub.cartservice.model.Cart;
import business.hub.cartservice.model.CartProduct;
import business.hub.cartservice.model.Product;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Интерфейс предоставляет методы для работы с корзиной пользователя.
 */
public interface CartService {

    Iterable<Product> findAllByKeepingLikeIgnoreCase(String filter);
    Iterable<Product> findAllByRating (long filter);
    Iterable<Product> findAllByStock (double filter);

    /**
     * Получает список продуктов в корзине пользователя по его идентификатору.
     *
     * @param userId идентификатор пользователя
     * @return список продуктов в корзине пользователя
     */
    List<CartProduct> getProductById(int userId);

    /**
     * Добавляет указанный продукт корзины в корзину пользователя.
     *
     * @param cartProduct продукт для добавления в корзину
     * @return добавленный продукт в корзине пользователя
     */
    CartProduct postUserCartProduct(CartProduct cartProduct);

    /**
     * Изменяет количество продукта в корзине пользователя.
     *
     * @param userId   идентификатор пользователя
     * @param productId идентификатор продукта
     * @param quantity  новое количество продукта
     * @return продукт в корзине пользователя с обновленным количеством
     */
    CartProduct putCartCount(int userId, int productId, int quantity);

    /**
     * Удаляет указанный продукт из корзины пользователя.
     *
     * @param userId    идентификатор пользователя
     * @param productId идентификатор продукта
     * @throws RuntimeException если продукт не найден в корзине пользователя
     */
    void delProductFromCart(int userId, int productId) throws RuntimeException;



    //returns all carts
    List<Cart> getAllCarts();
}

