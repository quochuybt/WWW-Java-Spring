package iuh.fit.springdatajdbc2.controller;

import iuh.fit.springdatajdbc2.dto.CustomerCity;
import iuh.fit.springdatajdbc2.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<CustomerCity> getCustomerGroupCity() {
        return customerService.getCustomerGroupCity();
    }
}
