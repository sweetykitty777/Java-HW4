package com.example.rest.services;

import com.example.rest.models.Dish;
import com.example.rest.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    public Dish createDish(Dish dish) {
        if (dish.getQuantity() > 0) {
            dish.setIs_available(true);
        } else {
            dish.setIs_available(false);
        }
        return dishRepository.save(dish);
    }

    public Dish getDishById(Integer id) {
        return dishRepository.findById(id).orElse(null);
    }

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    public Dish updateDish(Integer id, Dish dish) {
        Dish existingDish = getDishById(id);

        existingDish.setName(dish.getName());
        existingDish.setDescription(dish.getDescription());
        existingDish.setPrice(dish.getPrice());
        existingDish.setQuantity(dish.getQuantity());

        return dishRepository.save(existingDish);
    }

    public void deleteDish(Integer id) {
        Dish existingDish = getDishById(id);
        dishRepository.delete(existingDish);
    }
}
