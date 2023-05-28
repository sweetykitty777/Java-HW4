package com.example.rest.requests;

import com.example.rest.models.OrderDish;

import java.util.List;

public class CreateOrderRequest {
    private Long user_id;
    private String status;
    private String special_requests;

    private List<OrderDish> orderDishes;

    public void setSpecial_requests(String special_requests) {
        this.special_requests = special_requests;
    }

    public Long getUser_id() {
        return user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getSpecialRequests() {
        return special_requests;
    }

    public  List<OrderDish> getOrderDishes() {
        return orderDishes;
    }
}
