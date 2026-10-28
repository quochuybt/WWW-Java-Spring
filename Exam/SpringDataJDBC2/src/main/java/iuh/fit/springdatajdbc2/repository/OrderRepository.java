package iuh.fit.springdatajdbc2.repository;

import iuh.fit.springdatajdbc2.entity.Order;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    @Query("SELECT o.* FROM orders o JOIN customers c ON o.customer_id = c.id WHERE c.email = :email")
    List<Order> findOrderByCustomerEmail(@Param("email") String email);

    @Query("SELECT SUM(total_amount) FROM orders")
    Double getTotalSystem();

    @Query("SELECT p.name FROM products p JOIN order_details od ON p.id = od.product_id WHERE od.order_id = :id")
    List<String> getProductNameByOrderId(@Param("id") Long id);

}
