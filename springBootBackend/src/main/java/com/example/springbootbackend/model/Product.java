package com.example.springbootbackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    private String name;
    private String category;
    private String subcategory;
    private String brand;
    private String description;
    private float price;
    private float oldPrice;
    private int stock;
    private String colors;
    private String sizes;
    private String genre;
    private String imagerUrl;
    private String sportCategory;
}
