package com.kristjan.webshop.controller;

import com.kristjan.webshop.entity.Product;
import com.kristjan.webshop.exception.ProductNotFoundException;
import com.kristjan.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @PostMapping("products")
    public List<Product> addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return productRepository.findAll();
    }

    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable Long id) throws NoSuchElementException {
        if (!productRepository.existsById(id)) {
            throw new NoSuchElementException("Product not found");
        }
        return productRepository.findById(id).get();
    }

    @PutMapping("products/{id}")
    public List<Product> getProduct(@PathVariable Long id, @RequestBody Product product) {
        if (productRepository.existsById(id)) {
            productRepository.save(product);
        }
        return productRepository.findAll();
    }
}
