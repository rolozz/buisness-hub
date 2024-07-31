package business.hub.cartservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import business.hub.cartservice.model.Cart;
import business.hub.cartservice.model.CartProduct;
import business.hub.cartservice.model.Product;
import business.hub.cartservice.model.User;
import business.hub.cartservice.repositories.CartProductRepositories;
import business.hub.cartservice.repositories.CartRepositories;
import business.hub.cartservice.repositories.ProductRepositories;
import business.hub.cartservice.repositories.UserRepositories;
import business.hub.cartservice.service.CartServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class CartServiceTest2 {

    @MockBean
    private UserRepositories userRepositories;

    @MockBean
    private ProductRepositories productRepositories;

    @MockBean
    private CartRepositories cartRepositories;

    @MockBean
    private CartProductRepositories cartProductRepositories;

    @Autowired
    private CartServiceImpl cartService;

    private User user;
    private Product product;
    private Cart cart;
    private CartProduct cartProduct;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        user = new User();
        user.setId(1);
        user.setUsername("Test User");
        user.setCart(new Cart());

        product = new Product();
        product.setId(1);
        product.setName("Test Product");
        product.setDescription("description");
        product.setManufacturer("manufacturer");
        product.setWeight(5);
        product.setPrice(100);

        cartProduct = new CartProduct();
        cartProduct.setId(1);
        cartProduct.setCart(user.getCart());
        cartProduct.setProduct(product);
        cartProduct.setQuantity(1);

        //when(userRepositories.findById(user.getId())).thenReturn(Optional.of(user));
        //when(productRepositories.findById(product.getId())).thenReturn(Optional.of(product));
    }

    @Test
    public void testAddProductToCart() {
        when(cartProductRepositories.save(any(CartProduct.class))).thenReturn(cartProduct);

        CartProduct result = cartService.postUserCartProduct(cartProduct);

        assertNotNull(result);
        assertEquals(product.getId(), result.getProduct().getId());
        assertEquals(1, result.getQuantity());
        verify(cartProductRepositories, times(1)).save(any(CartProduct.class));
    }

    @Test
    public void testGetProductById() {

        List<CartProduct> expected = Arrays.asList(cartProduct);

        when(userRepositories.findById(user.getId())).thenReturn(Optional.of(user));
        when(cartProductRepositories.findAll()).thenReturn(expected);

        List<CartProduct> result = cartService.getProductById(user.getId());

        assertEquals(expected, result);
        verify(userRepositories, times(1)).findById(user.getId());
        verify(cartProductRepositories, times(1)).findAll();

    }

    @Test
    public void testRemoveProductFromCart() {
        when(userRepositories.findById(user.getId())).thenReturn(Optional.of(user));
        when(productRepositories.findById(product.getId())).thenReturn(Optional.of(product));
        when(cartProductRepositories.findAll()).thenReturn(List.of(cartProduct));
        doNothing().when(cartProductRepositories).delete(cartProduct);

        cartService.delProductFromCart(user.getId(), product.getId());

        verify(userRepositories, times(1)).findById(user.getId());
        verify(productRepositories, times(1)).findById(product.getId());
        verify(cartProductRepositories, times(1)).delete(cartProduct);
    }

}