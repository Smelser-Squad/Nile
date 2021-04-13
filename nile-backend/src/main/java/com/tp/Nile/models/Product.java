package com.tp.Nile.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    @JsonBackReference
    private Type type;

    @Column(name = "price",nullable = false)
    private Double price;

    @Column(name ="name",nullable = false)
    private String name;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "brand",nullable = false)
    private String brand;

    @OneToMany(fetch = FetchType.EAGER,
        cascade = CascadeType.ALL,
        mappedBy = "product",
        orphanRemoval = true)
    @JsonManagedReference
    private Set<Question> questions = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "order_product", joinColumns = { @JoinColumn(name = "order_id") }, inverseJoinColumns = { @JoinColumn(name = "product_id") })
    private List<Order> orders;

    public Product(){

    }

    public Product(Integer productId, Integer categoryId, Integer vendorId, Type type, Double price, String name, String description, String brand) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.vendorId = vendorId;
        this.type = type;
        this.price = price;
        this.name = name;
        this.description = description;
        this.brand = brand;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
