package iuh.fit.springdatajdbc2.controller;

import iuh.fit.springdatajdbc2.entity.Order;
import iuh.fit.springdatajdbc2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/email")
    public List<Order> findOrderByCustomerEmail(@RequestParam String email) {
        return orderService.findOrderByCustomerEmail(email);
    };

    @GetMapping("/total")
    public Double getTotalSystem() {
        return orderService.getTotalSystem();
    };

    @GetMapping("/orderId/{id}")
    public List<String> getProductNameByOrderId(@PathVariable Long id) {
        return orderService.getProductNameByOrderId(id);
    };
}
