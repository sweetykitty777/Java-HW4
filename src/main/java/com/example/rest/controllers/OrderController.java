package com.example.rest.controllers;

import com.example.rest.models.Order;
import com.example.rest.models.OrderDish;
import com.example.rest.requests.CreateOrderRequest;
import com.example.rest.repositories.OrderDishRepository;
import com.example.rest.repositories.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderDishRepository orderDishRepository;

    public OrderController(OrderRepository orderRepository, OrderDishRepository orderDishRepository) {
        this.orderRepository = orderRepository;
        this.orderDishRepository = orderDishRepository;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        // Проверка данных заказа и обработка ошибок

        // Создание объекта заказа и установка значений
        Order order = new Order();
        order.setUser(createOrderRequest.getUser_id());
        order.setStatus(createOrderRequest.getStatus());
        order.setSpecialRequests(createOrderRequest.getSpecialRequests());

        // Сохранение заказа в базу данных
        orderRepository.save(order);

        // Создание объектов связанных блюд и их сохранение в базу данных
        for (OrderDish orderDishRequest : createOrderRequest.getOrderDishes()) {
            OrderDish orderDish = new OrderDish();
            orderDish.setOrder(order);
            orderDish.setDish(orderDishRequest.getDish());
            orderDish.setQuantity(orderDishRequest.getQuantity());
            orderDish.setPrice(orderDishRequest.getDish().getPrice());
            orderDishRepository.save(orderDish);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
