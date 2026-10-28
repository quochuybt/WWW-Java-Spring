package iuh.fit.springdatajdbc2.repository;

import iuh.fit.springdatajdbc2.entity.Product;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("select p.id, p.name, p.price, p.stock from products p join categories c on c.id = p.category_id where c.name = :name")
    List<Product> findProductByCategoryId(@Param("name")String name);

    @Query("select * from products where stock<20")
    List<Product> findProductByStockLessThan20();

    @Query("select sum(p.price*p.stock) from products p join categories c on c.id = p.category_id where c.name = :catName ")
    Double getSumTotalStock(@Param("catName") String catName);

    @Query("SELECT * from products order by price desc limit 3")
    List<Product> findTop3();

    Product findProductById(Long id);
}
