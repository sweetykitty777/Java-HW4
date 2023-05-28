package com.example.rest.controllers;

import com.example.rest.models.Dish;
import com.example.rest.models.DishDTO;
import com.example.rest.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private DishService dishService;

    @GetMapping
    public ResponseEntity<?> getMenu() {
        List<Dish> dishes = dishService.getAllDishes();
        List<DishDTO> menu = dishes.stream()
                .filter(Dish::isAvailable)
                .map(dish -> new DishDTO(dish.getId(), dish.getName(), dish.getDescription(), dish.getPrice()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(menu);
    }
}
