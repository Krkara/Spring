package com.kristjan.salad.repository;

import com.kristjan.salad.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, String> {
}
