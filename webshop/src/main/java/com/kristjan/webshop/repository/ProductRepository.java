package com.kristjan.webshop.repository;

import com.kristjan.webshop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findAllByOrderById(Pageable pageable);

    List<Product> findAllByOrderById();
}
