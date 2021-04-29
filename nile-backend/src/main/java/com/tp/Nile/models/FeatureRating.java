package com.tp.Nile.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "feature_ratings")
public class FeatureRating implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rating_id")
    private Integer ratingId;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Feature.class)
    @JoinColumn(name = "feature_id")
    @JsonBackReference
    private Feature feature;

    @Column(name = "rating", nullable = false)
    private Integer rating;

}
