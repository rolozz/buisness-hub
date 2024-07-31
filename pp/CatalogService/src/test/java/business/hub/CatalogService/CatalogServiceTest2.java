package business.hub.CatalogService;

import business.hub.models.Product;
import business.hub.repositories.ProductsRepository;
import business.hub.services.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CatalogServiceTest2 {

    private final int productId = 1;

    private final String productName = "Test Product";

    private final double productPrice = 100.0;

    private final int productWeight = 12;

    private final double productVolume = 43.0;

    @Autowired
    private ProductServiceImpl productService;

    @MockBean
    private ProductsRepository productsRepositoryMock;

    private Product product;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        product = new Product();
        product.setProductId(productId);
        product.setProductName(productName);
        product.setProductPrice(productPrice);
        product.setProductWeight(productWeight);
        product.setProductVolume(productVolume);
        product.setCreatedAt(LocalDateTime.now().minusDays(1));
    }

    @Test
    public void testGetAllProducts() {
        List<Product> products = Arrays.asList(product);
        when(productsRepositoryMock.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(productId, result.get(0).getProductId());
        verify(productsRepositoryMock, times(1)).findAll();
    }

    @Test
    public void testGetProductById() {
        when(productsRepositoryMock.findById(productId)).thenReturn(Optional.of(product));

        Product result = productService.getProduct(productId);

        assertNotNull(result);
        assertEquals(productId, result.getProductId());
        assertEquals(productName, result.getProductName());
        verify(productsRepositoryMock, times(1)).findById(productId);
    }

    @Test
    public void testSaveProduct() {
        when(productsRepositoryMock.save(any(Product.class))).thenReturn(product);

        productService.saveProduct(product);

        assertNotNull(product);
        assertEquals(productId, product.getProductId());
        assertEquals(productName, product.getProductName());
        verify(productsRepositoryMock, times(1)).save(product);
    }

    @Test
    public void testUpdateProduct() {
        when(productsRepositoryMock.findById(productId)).thenReturn(Optional.of(product));
        when(productsRepositoryMock.save(any(Product.class))).thenAnswer(i -> i.getArguments()[0]);

        String updatedName = "Updated Product Name";
        double updatedPrice = 150.0;
        int updatedWeight = 15;
        double updatedVolume = 50.0;

        Product updatedProduct = new Product();
        updatedProduct.setProductName(updatedName);
        updatedProduct.setProductPrice(updatedPrice);
        updatedProduct.setProductWeight(updatedWeight);
        updatedProduct.setProductVolume(updatedVolume);

        productService.updateProduct(productId, updatedProduct);

        verify(productsRepositoryMock, times(1)).findById(productId);
        verify(productsRepositoryMock, times(1)).save(any(Product.class));

        assertEquals(productId, updatedProduct.getProductId());
        assertEquals(product.getCreatedAt(), updatedProduct.getCreatedAt());
        assertEquals(updatedName, updatedProduct.getProductName());
        assertEquals(updatedPrice, updatedProduct.getProductPrice());
        assertEquals(updatedWeight, updatedProduct.getProductWeight());
        assertEquals(updatedVolume, updatedProduct.getProductVolume());
    }

    @Test
    public void testDeleteProduct() {
        doNothing().when(productsRepositoryMock).deleteById(productId);

        productService.deleteProduct(productId);

        verify(productsRepositoryMock, times(1)).deleteById(productId);
    }
}
