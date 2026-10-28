package iuh.fit.springdatajdbc2.repository;

import iuh.fit.springdatajdbc2.dto.CustomerCity;
import iuh.fit.springdatajdbc2.entity.Customer;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Query("SELECT city, count(*) as sum from customers group by city")
    List<CustomerCity> getCustomerGroupCity();
}
