package com.kristjan.webshop.controller;

import com.kristjan.webshop.cache.ProductCache;
import com.kristjan.webshop.entity.Product;
import com.kristjan.webshop.exception.NotEnoughInStockException;
import com.kristjan.webshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutionException;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCache productCache;

    @GetMapping("products")
    public List<Product> getProducts() {
        return productRepository.findAllByOrderById();
    }

    @GetMapping("public-products")
    public Page<Product> getProductsByPage(Pageable pageable) {
        return productRepository.findAllByOrderById(pageable);
    }

    @PostMapping("products")
    public List<Product> addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return productRepository.findAllByOrderById();
    }

    @DeleteMapping("products/{id}")
    public List<Product> deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
        return productRepository.findAllByOrderById();
    }

    @GetMapping("products/{id}")
    public Product getProduct(@PathVariable Long id) throws NoSuchElementException, ExecutionException {
        if (!productRepository.existsById(id)) {
            throw new NoSuchElementException("Product not found");
        }
        return productCache.getProduct(id);
    }

    @PutMapping("products/{id}")
    public List<Product> editProduct(@PathVariable Long id, @RequestBody Product product) {
        if (productRepository.existsById(id)) {
            productRepository.save(product);
        }
        return productRepository.findAllByOrderById();
    }

    @PatchMapping("increase-stock/{id}")
    public List<Product> increaseStock(@PathVariable Long id) throws ExecutionException {
        Product product = productCache.getProduct(id);
        product.setStock(product.getStock()+1);
        productRepository.save(product);
        productCache.refreshProduct(id, product);
        return productRepository.findAllByOrderById();
    }

    @PatchMapping("decrease-stock/{id}")
    public List<Product> decreaseStock(@PathVariable Long id) throws NotEnoughInStockException, ExecutionException {
        Product product = productCache.getProduct(id);
        if (product.getStock() > 0) {
            product.setStock(product.getStock()-1);
            productRepository.save(product);
            productCache.refreshProduct(id, product);
        } else {
            throw new NotEnoughInStockException("Kogus ei saa miinusesse minna");
        }
        return productRepository.findAllByOrderById();
    }
}
