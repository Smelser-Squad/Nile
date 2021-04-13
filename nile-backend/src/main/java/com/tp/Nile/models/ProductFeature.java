package com.tp.Nile.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductFeature implements Serializable {

    @EmbeddedId
    private ProductFeatureId id = new ProductFeatureId();

    @ManyToOne
    @MapsId("featureId")
    private Feature feature;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    @Column(name="rating", nullable = false)
    private Integer rating;

}
