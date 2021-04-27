package com.tp.Nile.repositories;

import com.tp.Nile.models.ReviewPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewPhotoRepository extends JpaRepository<ReviewPhoto, Integer> {
    List<ReviewPhoto> getPhotosByReview(Integer reviewId);
}
