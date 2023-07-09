package com.kristjan.salad.repository;

import com.kristjan.salad.domain.RecipeComponent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeComponentRepository extends JpaRepository<RecipeComponent, Long> {
}
