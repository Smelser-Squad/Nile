package com.tp.Nile.controllers.requests;

import com.tp.Nile.models.ProductPhoto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AddProductRequest {

    private Integer categoryId;
    private Integer typeId;
    private Integer orderId;
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
