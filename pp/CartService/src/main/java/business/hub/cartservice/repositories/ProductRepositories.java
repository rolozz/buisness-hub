package business.hub.cartservice.repositories;

import business.hub.cartservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepositories extends JpaRepository<Product, Integer> {

Iterable<Product> findAllByKeepingLikeIgnoreCase(String filter);
@Query(value = "select p from Product p where p.rating = :filter")
Iterable<Product> findAllByRating (@Param("filter")long filter);

@Query(value = "select * from products where product_stock = :filter", nativeQuery = true)
Iterable<Product> findAllByStock (@Param("filter")double filter);

}
