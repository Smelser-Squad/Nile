package com.tp.Nile.models;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "category_id", nullable = false)
    private Integer categoryId;

    @Column(name = "vendor_id",nullable = false)
    private Integer vendorId;

    @Column(name ="type",nullable = false)
    private String type;

    @Column(name = "price",nullable = false)
    private Double price;

    @Column(name ="name",nullable = false)
    private String name;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "brand",nullable = false)
    private String brand;

    public Product(Integer productId, Integer categoryId, Integer vendorId, String type, Double price, String name, String description, String brand) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.vendorId = vendorId;
        this.type = type;
        this.price = price;
        this.name = name;
        this.description = description;
        this.brand = brand;
    }

}
