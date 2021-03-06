package com.tp.Nile.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review")
public class Review implements Serializable {

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "review_date", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @UpdateTimestamp
    private LocalDate reviewDate;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference(value = "review-photos")
    private List<ReviewPhoto> reviewPhotos = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @JsonBackReference(value = "product-reviews")
    private Product product;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "feature_id")
    private Feature feature;

    @Column(name="rating", nullable = false)
    private Integer rating;

    @Column(name = "helpful")
    private boolean helpful;

    public List<ReviewPhoto> setReviewPhotos(List<ReviewPhoto> photos) {
        for (ReviewPhoto p : photos) {
            addReviewPhoto(p);
        }
        return getReviewPhotos();
    }

    public void addReviewPhoto(ReviewPhoto photo) {
        getReviewPhotos().add(photo);
        photo.setReview(this);
    }

    public void removeReviewPhoto(ReviewPhoto photo) {
        getReviewPhotos().remove(photo);
        photo.setReview(null);
    }
}