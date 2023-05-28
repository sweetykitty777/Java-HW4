package com.example.rest.repositories;


import com.example.rest.models.OrderDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderDishRepository extends JpaRepository<OrderDish, Integer> {
    Optional<OrderDish> findById(Long id);
}
