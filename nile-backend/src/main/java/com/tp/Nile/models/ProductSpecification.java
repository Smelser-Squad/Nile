package com.tp.Nile.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSpecification implements Serializable {
    @EmbeddedId
    private ProductSpecificationId id = new ProductSpecificationId();

    @ManyToOne
    @MapsId("productId")
    @JsonIgnoreProperties(value = {"productSpecs"})
    private Product product;

    @ManyToOne
    @MapsId("specId")
    private Specification spec;

    @Column(name = "value", nullable = false)
    private String value;
}
