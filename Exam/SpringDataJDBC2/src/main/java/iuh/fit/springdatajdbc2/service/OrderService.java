package iuh.fit.springdatajdbc2.service;

import iuh.fit.springdatajdbc2.entity.Order;
import iuh.fit.springdatajdbc2.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findOrderByCustomerEmail(String email) {
        return orderRepository.findOrderByCustomerEmail(email);
    };

    public Double getTotalSystem() {
        return orderRepository.getTotalSystem();
    };

    public List<String> getProductNameByOrderId(Long id) {
        return orderRepository.getProductNameByOrderId(id);
    };
}
