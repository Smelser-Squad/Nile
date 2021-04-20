package com.tp.Nile.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = { CascadeType.MERGE })
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
    @JoinColumn(name = "type_id")
    @JsonIgnoreProperties(value = {"products"})
    private Type type;

    @Column(name = "price", nullable = false)
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 10, fraction = 2)
    private BigDecimal price;

    @Column(name = "name", nullable = false)
    @Size(min = 5)
    private String name;

    @Column(name = "description", nullable = false)
    @Size(min = 10)
    private String description;

    @Column(name = "brand", nullable = false)
    @Size(min = 3)
    private String brand;

    @Column(name = "stock", nullable = false)
    @Min(value = 0, message = "Stock must be greater than or equal to 0")
    private Integer stock;

    @Column(name = "primeEligible", nullable = false)
    private boolean primeEligible;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    private List<ProductPhoto> photos;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "product_feature",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id"))
    private List<Feature> features;

    @OneToMany(mappedBy = "product")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ProductOrder> productOrders;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    private List<Review> reviews;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "product",
            orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Question> questions;

    @OneToMany(mappedBy = "product")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ProductSpecification> productSpecs;
}
