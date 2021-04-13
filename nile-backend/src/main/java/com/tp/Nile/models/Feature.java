package com.tp.Nile.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Feature implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feature_id")
    private Integer featureId;

    @Column(name ="name",nullable = false)
    private String name;


    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "product_feature", joinColumns = { @JoinColumn(name = "product_id") }, inverseJoinColumns = { @JoinColumn(name = "feature_id") })
    private List<Order> orders;

    @OneToMany(mappedBy = "feature")
    private Set<ProductFeature> productFeatures = new HashSet<>();

}
