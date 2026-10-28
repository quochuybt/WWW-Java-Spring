package iuh.fit.springdatajdbc2.service;

import iuh.fit.springdatajdbc2.entity.Product;
import iuh.fit.springdatajdbc2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Iterable<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Long id) {
        return productRepository.findProductById(id);
    }

    public List<Product> findProductByCategoryId(String name) {
        return productRepository.findProductByCategoryId(name);
    };

    public List<Product> findProductByStockLessThan20() {
        return productRepository.findProductByStockLessThan20();
    };

    public Double getSumTotalStock(String catName) {
        return productRepository.getSumTotalStock(catName);
    };

    public List<Product> findTop3() {
        return productRepository.findTop3();
    };
}
