package com.tp.Nile.models;

import javax.persistence.*;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name= "product_photo")

public class ProductPhoto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Integer photoId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_product_id", referencedColumnName = "product_id")
    @JsonIgnoreProperties(value = {"photoList"})

    @JsonBackReference
    private Product product;

    @Column(name = "image_src", nullable = false)
    private String imageSrc;

    @Column(name = "color", nullable = false)
    private String color;

}
