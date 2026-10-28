package iuh.fit.springdatajdbc1.controller;

import iuh.fit.springdatajdbc1.entity.Product;
import iuh.fit.springdatajdbc1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Iterable<Product> getAll() {
        return productService.getAll();
    }

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @GetMapping("/{id}")
    public List<Product> getProductByCategoryId(@PathVariable Long id) {
        return productService.getProductByCategoryId(id);
    };

    @GetMapping("/filter")
    public List<Product> getProductByPriceGreaterThan(@RequestParam double price) {
        return productService.getProductByPriceGreaterThan(price);
    };
}
