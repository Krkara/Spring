package com.kristjan.webshop.repository;

import com.kristjan.webshop.entity.OrderRow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRowRepository extends JpaRepository<OrderRow, Long> {
}
