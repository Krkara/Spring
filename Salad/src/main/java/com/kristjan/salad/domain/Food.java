package com.kristjan.salad.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Food {
    @Id
    private String name;
    private double proteinPercentage;
    private double fatPercentage;
    private double carbohydratesPercentage;
}
