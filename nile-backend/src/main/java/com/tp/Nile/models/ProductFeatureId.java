package com.tp.Nile.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
public class ProductFeatureId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer productId;
    private Integer featureId;

}