package com.example.rest.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "dishes",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    private BigDecimal price;

    private int quantity;

    private Boolean is_available;
    public Dish() {
        // конструктор без параметров
    }

    public Dish(String name, String description, BigDecimal price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        if (quantity > 0) {
            is_available = true;
        } else {
            is_available = false;
        }
    }

    public void setIs_available(Boolean a) {
        is_available = a;
    }

    public int getQuantity() {
        return quantity;
    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isAvailable() {
        return is_available;
    }
}
