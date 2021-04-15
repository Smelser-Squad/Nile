package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Review;

import java.util.List;

public interface ReviewService {

    List<Review> getAllReviews()
            throws InvalidReviewIdException, NullReviewIdException, NullReviewAttributeException;

    List<Review> getReviewsByUserId(Integer userId)
            throws InvalidUserIdException, NullUserIdException, NullReviewAttributeException;

    Review getReviewById(Integer reviewId)
            throws InvalidReviewIdException, NullReviewIdException, NullReviewAttributeException;

    Review addReview(Review newReview)
            throws InvalidReviewIdException, NullReviewIdException, NullReviewAttributeException;

    Review updateReview(Review updatedReview)
            throws InvalidReviewIdException, NullReviewIdException, NullReviewAttributeException;

    boolean deleteReview(Integer reviewId)
            throws InvalidReviewIdException, NullReviewIdException, NullReviewAttributeException;

}
