package iuh.fit.springdatajdbc2.service;

import iuh.fit.springdatajdbc2.dto.CustomerCity;
import iuh.fit.springdatajdbc2.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    public List<CustomerCity> getCustomerGroupCity() {
        return customerRepository.getCustomerGroupCity();
    }
}
