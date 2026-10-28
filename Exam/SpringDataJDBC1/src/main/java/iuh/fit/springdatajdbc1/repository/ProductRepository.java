package iuh.fit.springdatajdbc1.repository;

import iuh.fit.springdatajdbc1.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
    List<Product> findProductByCategoryId(Long id);
    List<Product> findProductByPriceGreaterThan(double price);
}
