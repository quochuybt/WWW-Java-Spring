package iuh.fit.springdatajdbc1.service;

import iuh.fit.springdatajdbc1.entity.Product;
import iuh.fit.springdatajdbc1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProductByCategoryId(Long id) {
        return productRepository.findProductByCategoryId(id);
    };
    public List<Product> getProductByPriceGreaterThan(double price) {
        return productRepository.findProductByPriceGreaterThan(price);
    };
}
