package iuh.fit.springdatajdbc2.controller;

import iuh.fit.springdatajdbc2.entity.Product;
import iuh.fit.springdatajdbc2.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product save(@RequestBody Product product) {
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @GetMapping
    public Iterable<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @GetMapping("/category")
    public List<Product> findProductByCategoryId(@RequestParam String name) {
        return productService.findProductByCategoryId(name);
    };

    @GetMapping("/filter")
    public List<Product> findProductByStockLessThan20() {
        return productService.findProductByStockLessThan20();
    };

    @GetMapping("/sumToTal")
    public Double getSumTotalStock(@RequestParam String catName) {
        return productService.getSumTotalStock(catName);
    };

    @GetMapping("/top3")
    public List<Product> findTop3() {
        return productService.findTop3();
    };
}
