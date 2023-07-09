package com.kristjan.salad.domain;

import com.kristjan.salad.domain.Food;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class RecipeComponent {
    @Id
    private Long id;
    @ManyToOne
    private Food food;
    private double amount;
}

