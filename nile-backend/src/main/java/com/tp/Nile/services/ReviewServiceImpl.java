package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Review;
import com.tp.Nile.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ReviewServiceImpl implements ReviewService{

    @Autowired
    ReviewRepository repo;

    @Override
    public List<Review> getAllReviews() {
        return repo.findAll();
    }

    @Override
    public List<Review> getReviewsByUserId(Integer userId) throws InvalidUserIdException, NullUserIdException {
        if(userId==null){
            throw new NullUserIdException("Cannot get reviews with null user id");
        }

        List<Review> retrieved=null;

        List<Review> review=repo.findAll();

        if(!review.isEmpty()){
            retrieved = review;
            return retrieved;
        }else{
            throw new InvalidUserIdException("Reviews with that user id does not exist");
        }
    }

    @Override
    public Review getReviewById(Integer reviewId) throws NullReviewIdException, InvalidReviewIdException {
        if(reviewId==null){
            throw new NullReviewIdException("Cannot get review with null id");
        }

        Review retrieved=null;

        Optional<Review> review=repo.findById(reviewId);

        if(review.isPresent()){
            retrieved=review.get();
            return retrieved;
        }else{
            throw new InvalidReviewIdException("Product with that id does not exist");
        }
    }

    @Override
    public Review addReview(Review newReview) {
        return repo.saveAndFlush(newReview);
    }

    @Override
    public Review updateReview(Review updatedReview) {
        return repo.saveAndFlush(updatedReview);
    }

    @Override
    public boolean deleteReview(Integer reviewId) throws NullReviewIdException, InvalidReviewIdException {
        if(reviewId==null){
            throw new NullReviewIdException("Cannot delete review with null id");
        }
        Review retreived=repo.findById(reviewId).get();

        if(retreived!=null){
            repo.delete(retreived);
            return true;
        }else{
            throw new InvalidReviewIdException("Review with that id does not exist");
        }
    }
}
