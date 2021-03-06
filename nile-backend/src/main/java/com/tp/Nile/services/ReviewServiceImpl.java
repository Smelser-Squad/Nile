package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Review;
import com.tp.Nile.models.ReviewPhoto;
import com.tp.Nile.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    ReviewRepository repo;

    @Autowired
    ReviewPhotoServiceImpl photoService;

    @Override
    public List<Review> getAllReviews()
            throws InvalidReviewIdException, NullReviewIdException, NullReviewAttributeException{
        return repo.findAll();
    }

    @Override
    public List<Review> getReviewsByProductId(Integer productId) throws InvalidReviewIdException, NullReviewIdException, NullReviewAttributeException, InvalidProductIdException,  NullProductIdException {
        if(productId==null){
            throw new NullProductIdException("Cannot get reviews with null product id");
        }

        List<Review> retrieved=null;

        List<Review> review=repo.getReviewsByProductId(productId);

        if(!review.isEmpty()){
            retrieved = review;
            return retrieved;
        }else{
            throw new InvalidProductIdException("Reviews with that product id does not exist");
        }
    }

    @Override
    public List<Review> getReviewsByUserId(Integer userId)
            throws InvalidUserIdException, NullUserIdException, NullReviewAttributeException {

        if(userId==null){
            throw new NullUserIdException("Cannot get reviews with null user id");
        }

        List<Review> retrieved=null;

        List<Review> review=repo.findByUserUserId(userId);

        if(!review.isEmpty()){
            retrieved = review;
            return retrieved;
        }else{
            throw new InvalidUserIdException("Reviews with that user id does not exist");
        }
    }

    @Override
    public Review getReviewById(Integer reviewId)
            throws NullReviewIdException, InvalidReviewIdException, NullReviewAttributeException {
        if(reviewId==null){
            throw new NullReviewIdException("Cannot get review with null id");
        }

        Review retrieved=null;

        Optional<Review> review=repo.findById(reviewId);

        if(review.isPresent()){
            retrieved=review.get();
            if (retrieved == null)
                throw new NullReviewAttributeException("All non nullable attributes must have value");
            else
                return retrieved;
        }else{
            throw new InvalidReviewIdException("Review with that id does not exist");
        }
    }

    @Override
    public Review addReview(Review newReview)
            throws InvalidReviewIdException, NullReviewIdException, NullReviewAttributeException, InvalidReviewException {
        if(newReview.getRating() < 1 || newReview.getRating() > 5){
            throw new InvalidReviewException("A review's rating must be between 1 and 5");
        }
        return repo.saveAndFlush(newReview);
    }

    @Override
    public Review updateReview(Review updatedReview)
            throws InvalidReviewIdException, NullReviewIdException, NullReviewAttributeException, InvalidReviewException {
        if(updatedReview.getRating() < 1 || updatedReview.getRating() > 5){
            throw new InvalidReviewException("A review's rating must be between 1 and 5");
        }

        return repo.saveAndFlush(updatedReview);
    }

//    @Override
//    public List<Review> getReviewsByProductId(Integer productId) throws NullProductIdException, InvalidProductIdException {
//
//        if(productId==null) {
//            throw new NullProductIdException("Cannot get reviews with null product id");
//        }
//
//        List<Review> retrieved=null;
//
//        List<Review> review=repo.findByProductId(productId);
//
//        if(!review.isEmpty()){
//            retrieved = review;
//            return retrieved;
//        } else {
//            throw new InvalidProductIdException("Reviews with that product id does not exist");
//        }
//    }

    @Override
    public boolean deleteReview(Integer reviewId)
            throws NullReviewIdException, InvalidReviewIdException, NullReviewAttributeException {
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

    @Override
    public List<ReviewPhoto> getReviewPhotosByProductId(Integer productId)
            throws InvalidProductIdException, InvalidReviewIdException,
            NullProductIdException, NullReviewIdException, NullReviewAttributeException {
        List<ReviewPhoto> toReturn = new ArrayList<>();
        List<Review> reviews = getReviewsByProductId(productId);

        for (Review r : reviews) {
            List<ReviewPhoto> photos = photoService.getPhotosByReview(r.getReviewId());
            if (photos != null) toReturn.addAll(photos);
        }
        return toReturn;
    }


}
