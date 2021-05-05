package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Review;
import com.tp.Nile.models.ReviewPhoto;
import com.tp.Nile.repositories.ReviewPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewPhotoServiceImpl implements ReviewPhotoService {

    @Autowired
    ReviewPhotoRepository repo;

    @Autowired
    ReviewServiceImpl reviewService;

    @Override
    public List<ReviewPhoto> getAllPhotos() {
        return repo.findAll();
    }

    @Override
    public ReviewPhoto getPhotoById(Integer photoId) throws InvalidPhotoIdException {
        Optional<ReviewPhoto> photoOptional = repo.findById(photoId);
        if (photoOptional.isPresent()) {
            return photoOptional.get();
        } else {
            throw new InvalidPhotoIdException("No Photo with id " + photoId);
        }
    }

    @Override
    public List<ReviewPhoto> getPhotosByReview(Integer reviewId)
            throws NullReviewAttributeException, NullReviewIdException, InvalidReviewIdException {
        Review review = reviewService.getReviewById(reviewId);
        if (review != null) return review.getReviewPhotos();
        else return null;
    }

    @Override
    public ReviewPhoto addPhoto(ReviewPhoto newPhoto, Integer reviewId)
            throws NullReviewAttributeException, NullReviewIdException, InvalidReviewIdException {
        Review toAssociate = reviewService.getReviewById(reviewId);
        newPhoto.setReview(toAssociate);
        return repo.saveAndFlush(newPhoto);
    }

    @Override
    public ReviewPhoto updatePhoto(ReviewPhoto update) throws InvalidPhotoIdException {
        Optional<ReviewPhoto> updateOptional = repo.findById(update.getPhotoId());
        if (updateOptional.isPresent()) {
            ReviewPhoto toUpdate = updateOptional.get();
            toUpdate.setReview(update.getReview());
            toUpdate.setImageSrc(update.getImageSrc());
            return repo.saveAndFlush(toUpdate);
        } else {
            throw new InvalidPhotoIdException("Could not Update Review Photo with id: " + update.getPhotoId());
        }
    }

    @Override
    public boolean deletePhoto(Integer photoId) throws NullPhotoIdException, InvalidPhotoIdException {
        if (photoId == null) {
            throw new NullPhotoIdException("Can not delete photo with an ID of null.");
        }
        Optional<ReviewPhoto> retrieved = repo.findById(photoId);
        if (retrieved.isPresent()) {
            repo.delete(retrieved.get());
            return true;
        } else {
            throw new InvalidPhotoIdException("Photo with that ID does not exist.");
        }
    }
}
