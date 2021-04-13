package com.tp.Nile.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

public class ProductSpecification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_spec_id")
    private Integer productSpecId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "spec_id")
    @JsonBackReference
    private Specification spec;

    @Column(name = "value")
    private String value;

    public ProductSpecification(Product product, Specification spec, String value) {
        this.product = product;
        this.spec = spec;
        this.value = value;
    }

    public Integer getProductSpecId() {
        return productSpecId;
    }

    public void setProductSpecId(Integer productSpecId) {
        this.productSpecId = productSpecId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Specification getSpec() {
        return spec;
    }

    public void setSpec(Specification spec) {
        this.spec = spec;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
