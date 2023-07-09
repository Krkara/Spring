package com.kristjan.salad.controller;

import com.kristjan.salad.domain.Food;
import com.kristjan.salad.domain.Recipe;
import com.kristjan.salad.domain.RecipeComponent;
import com.kristjan.salad.repository.FoodRepository;
import com.kristjan.salad.repository.RecipeComponentRepository;
import com.kristjan.salad.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class RecipeController {
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    RecipeComponentRepository recipeComponentRepository;
    @Autowired
    RecipeRepository recipeRepository;

    // http://localhost:8080/addFood?name=Kartul&proteinPercentage=20&fatPercentage=10&carbohydratesPercentage=70
    @GetMapping("addFood")
    public void addFood( //
            @RequestParam String name,
            @RequestParam double proteinPercentage,
            @RequestParam double fatPercentage,
            @RequestParam double carbohydratesPercentage
    ) {
        if (proteinPercentage + fatPercentage + carbohydratesPercentage != 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The sum of percentages must be equal to 100.");
        }
        Food food = new Food(name, proteinPercentage, fatPercentage, carbohydratesPercentage);
        foodRepository.save(food);
    }
    @GetMapping("addComponent")
    public void addRecipeComponent(
            @RequestParam String name,
            @RequestParam double amount
    ) {
        Food foundFood = foodRepository.findById(name).get();
        RecipeComponent component = new RecipeComponent();
        component.setFood(foundFood);
        component.setAmount(amount);
        recipeComponentRepository.save(component);

    }

    @GetMapping("findFood")
    public String getFoodByName(
            @RequestParam String name
    ) {
        Food foundFood = foodRepository.findById(name).get();
        return foundFood.getName() + " exists in database";
    }

    @GetMapping("findRecipe")
    public String getRecipeByName(
            @RequestParam String name
    ) {
        Recipe foundRecipe = recipeRepository.findById(name).get();
        return foundRecipe.getName() + " exists in database";
    }

}
