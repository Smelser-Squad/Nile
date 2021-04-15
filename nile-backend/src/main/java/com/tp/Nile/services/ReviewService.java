package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidReviewIdException;
import com.tp.Nile.exceptions.InvalidUserIdException;
import com.tp.Nile.exceptions.NullReviewIdException;
import com.tp.Nile.exceptions.NullUserIdException;
import com.tp.Nile.models.Review;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ReviewService {

    List<Review> getAllReviews();

    List<Review> getReviewsByUserId(Integer userId) throws InvalidUserIdException, NullUserIdException;

    Review getReviewById(Integer reviewId) throws InvalidReviewIdException, NullReviewIdException;

    Review addReview(Review newReview);

    Review updateReview(Review updatedReview);

    boolean deleteReview(Integer reviewId) throws InvalidReviewIdException, NullReviewIdException;

}
