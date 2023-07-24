package com.kristjan.webshop.controller;

import com.kristjan.webshop.entity.Category;
import com.kristjan.webshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping
    public List<Category> addCategory(@RequestBody Category category) {
        categoryRepository.save(category);
        return categoryRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public List<Category> deleteCategory(@PathVariable Long id) {
        categoryRepository.deleteById(id);
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id) {
        return categoryRepository.findById(id).get();
    }

    @PutMapping("/{id}")
    public List<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        if (categoryRepository.existsById(id)) {
            category.setId(categoryRepository.findById(id).get().getId());
            categoryRepository.save(category);
        }
        return categoryRepository.findAll();
    }
}
