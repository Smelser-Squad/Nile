package com.tp.Nile.models;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @JoinColumn(name = "category_id")
    private Category category;

//    @Column(name = "vendor_id",nullable = false)
//    private Integer vendorId;

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

}
