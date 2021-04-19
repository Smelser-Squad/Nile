package com.tp.Nile.controllers.requests;

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
    private Integer reviewId;
    private List<Integer> photoId;
    private List<Integer> featureId;
    private String name;
    private String description;
    private Double price;
    private String brand;
    private Integer stock;
    private boolean primeEligible;
}
