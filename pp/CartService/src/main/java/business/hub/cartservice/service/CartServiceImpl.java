package business.hub.cartservice.service;

import business.hub.cartservice.enums.keeping;
import business.hub.cartservice.model.Cart;
import business.hub.cartservice.model.CartProduct;
import business.hub.cartservice.model.Product;
import business.hub.cartservice.model.User;
import business.hub.cartservice.repositories.CartProductRepositories;
import business.hub.cartservice.repositories.CartRepositories;
import business.hub.cartservice.repositories.ProductRepositories;
import business.hub.cartservice.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static business.hub.cartservice.enums.keeping.PRODUCT_WAREHOUSE;

/**
 * Реализация сервиса для работы с корзиной пользователя.
 */
@Service
public class CartServiceImpl implements CartService {

    private final CartRepositories cartRepositories;
    private final CartProductRepositories cartProductRepositories;
    private final ProductRepositories productRepositories;
    private final UserRepositories userRepositories;

    /**
     * Конструктор для CartServiceImpl.
     * @param cartRepositoriesParam - {@link CartRepositories}
     * @param cartProductRepositoriesParam - {@link CartProductRepositories}
     * @param productRepositoriesParam - {@link ProductRepositories}
     * @param userRepositoriesParam - {@link UserRepositories}
     */
    @Autowired
    public CartServiceImpl(final CartRepositories cartRepositoriesParam,
                           final CartProductRepositories cartProductRepositoriesParam,
                           final ProductRepositories productRepositoriesParam,
                           final UserRepositories userRepositoriesParam) {
        this.cartRepositories = cartRepositoriesParam;
        this.cartProductRepositories = cartProductRepositoriesParam;
        this.productRepositories = productRepositoriesParam;
        this.userRepositories = userRepositoriesParam;
    }

    @Override
    public Iterable<Product> findAllByKeepingLikeIgnoreCase(String filter) {
        if (filter != null && !filter.isBlank()) {
            return this.productRepositories.findAllByKeepingLikeIgnoreCase(filter);
        } else {
            return this.productRepositories.findAll();
        }
    }

    @Override
    public Iterable<Product> findAllByRating(long filter) {
        if (filter > 0 && filter < 10) {
            return this.productRepositories.findAllByRating(filter);
        } else {
            return this.productRepositories.findAll();
        }
    }

    @Override
    public Iterable<Product> findAllByStock(double filter) {
        if (filter > 0 && filter < 1) {
            return this.productRepositories.findAllByStock(filter);
        } else {
            return this.productRepositories.findAll();
        }
    }

    /**
     * Получает список продуктов в корзине пользователя по его идентификатору.
     *
     * @param userid идентификатор пользователя
     * @return список продуктов в корзине пользователя
     */
    @Override
    public List<CartProduct> getProductById(final int userid) {
        User user = userRepositories.findById(userid).orElse(null);
        if (user != null) {
            return cartProductRepositories.findAll().stream().filter(cartProduct ->
                    cartProduct.getCart().equals(user.getCart())
            ).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    /**
     * Добавляет указанный продукт корзины в корзину пользователя.
     *
     * @param cartProduct продукт для добавления в корзину
     * @return добавленный продукт в корзине пользователя
     */
    @Override
    public CartProduct postUserCartProduct(final CartProduct cartProduct) {
        productRepositories.save(cartProduct.getProduct());

        if (cartProduct.getProduct().getKeeping().equals(PRODUCT_WAREHOUSE.getKeeping())) {
            cartProduct.setComment("Данный продукт только по предоплате!!! Т.к находится на складе!!!" + "\n" + "При оформлении данного продукта доставка в магазин займет 3 дня." + "\n" + "Примерная дата доставки ожидается:" + LocalDate.now().plusDays(3));}
            if (cartProduct.getProduct().getStock() != 1.0){
           cartProduct.getProduct().setPrice(cartProduct.getProduct().getPrice() * cartProduct.getProduct().getStock());
        }
        cartProductRepositories.save(cartProduct);
        return cartProduct;
    }

    /**
     * Изменяет количество продукта в корзине пользователя.
     *
     * @param userId   идентификатор пользователя
     * @param productId идентификатор продукта
     * @param quantity  новое количество продукта
     * @return продукт в корзине пользователя с обновленным количеством
     */
    @Override
    public CartProduct putCartCount(final int userId,
                                    final int productId,
                                    final int quantity) {
        User user = userRepositories.findById(userId).orElse(null);
        Product product = productRepositories.findById(productId).orElse(null);
        if (user != null) {
            CartProduct cartProduct1 = cartProductRepositories.findAll().stream().filter(cartProduct ->
                    cartProduct.getCart().equals(user.getCart())
            ).filter(cartProduct ->
                    cartProduct.getProduct().equals(product)
            ).toList().get(0);
            cartProduct1.setQuantity(quantity);
            return cartProduct1;
        }
        return new CartProduct();
    }

    /**
     * Удаляет указанный продукт из корзины пользователя.
     *
     * @param userId    идентификатор пользователя
     * @param productId идентификатор продукта
     * @throws RuntimeException если продукт не найден в корзине пользователя
     */
    @Override
    public void delProductFromCart(final int userId, final int productId)
            throws RuntimeException {
        User user = userRepositories.findById(userId).orElse(null);
        Product product = productRepositories.findById(productId).orElse(null);
        if (user != null) {
            CartProduct cartProduct1 = cartProductRepositories.findAll().stream().filter(cartProduct ->
                    cartProduct.getCart().equals(user.getCart())
            ).filter(cartProduct ->
                    cartProduct.getProduct().equals(product)
            ).toList().get(0);
            cartProductRepositories.delete(cartProduct1);
        } else {
            throw new RuntimeException("User null");
        }
    }

    /**
    * a method to get all carts for testing puposes
    */
    @Override
    public List<Cart> getAllCarts(){
        return cartRepositories.findAll();
    }
}
