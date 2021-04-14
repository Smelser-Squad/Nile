package com.tp.Nile.services;

import com.tp.Nile.models.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews();
    Review getReviewById(Integer reviewId);
    void addReview(Review toAdd);
    void updateReview(Review toUpdate);
    void deleteReview(Integer reviewId);


}
