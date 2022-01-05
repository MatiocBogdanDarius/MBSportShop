package com.example.springbootbackend.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "purchased_product")
public class PurchasedProduct {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private long productId;
    private long userId;
    private String color;
    private String size;
    private long quantity;

    public PurchasedProduct(long productId, long userId, String color, String size, long quantity) {
        this.productId = productId;
        this.userId = userId;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
    }
}
