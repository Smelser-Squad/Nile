package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Review;
import com.tp.Nile.models.ReviewPhoto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ReviewService {

    List<Review> getAllReviews()
            throws InvalidReviewIdException, NullReviewIdException, NullReviewAttributeException;

    List<Review> getReviewsByUserId(Integer userId)
            throws InvalidUserIdException, NullUserIdException, NullReviewAttributeException;

    List<Review> getReviewsByProductId(Integer productId) throws NullProductIdException, InvalidProductIdException, InvalidReviewIdException, NullReviewIdException, NullReviewAttributeException;

    Review getReviewById(Integer reviewId)
            throws InvalidReviewIdException, NullReviewIdException, NullReviewAttributeException;

    Review addReview(Review newReview)
            throws InvalidReviewIdException, NullReviewIdException, NullReviewAttributeException, InvalidReviewException;

    Review updateReview(Review updatedReview)
            throws InvalidReviewIdException, NullReviewIdException, NullReviewAttributeException, InvalidReviewException;

    boolean deleteReview(Integer reviewId)
            throws InvalidReviewIdException, NullReviewIdException, NullReviewAttributeException;

    List<ReviewPhoto> getReviewPhotosByProductId(Integer productId) throws InvalidProductIdException, InvalidReviewIdException, NullProductIdException, NullReviewIdException, NullReviewAttributeException;

}
