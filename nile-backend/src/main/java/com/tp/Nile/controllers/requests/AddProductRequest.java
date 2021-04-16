package com.tp.Nile.controllers.requests;

import com.tp.Nile.models.Feature;
import com.tp.Nile.models.ProductPhoto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
public class AddProductRequest {

    private Integer categoryId;
    private Integer typeId;
    private Integer vendorId;
    private List<ProductPhoto> photos;
//    private Set<Feature> features;

    private String name;
    private String description;
    private Double price;
    private String brand;
    private Integer stock;
    private boolean primeEligible;


}
