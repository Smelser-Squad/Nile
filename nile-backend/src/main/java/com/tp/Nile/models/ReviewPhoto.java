package com.tp.Nile.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "review_photo")
@JsonIgnoreProperties(value = {"product"}, allowSetters = true)
public class ReviewPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Integer photoId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_review_id")
    @JsonBackReference
    private Review review;

    @Column(name = "image_src", nullable = false)
    private String imageSrc;
}
