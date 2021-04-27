package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.ReviewPhoto;

import java.util.List;

public interface ReviewPhotoService {
    List<ReviewPhoto> getAllPhotos();
    ReviewPhoto getPhotoById(Integer photoId) throws InvalidPhotoIdException;
    List<ReviewPhoto> getPhotosByReview(Integer reviewId) throws NullReviewAttributeException, NullReviewIdException, InvalidReviewIdException;
    ReviewPhoto addPhoto(ReviewPhoto newPhoto, Integer reviewId) throws NullReviewAttributeException, NullReviewIdException, InvalidReviewIdException;
    ReviewPhoto updatePhoto(ReviewPhoto toUpdate) throws InvalidPhotoIdException;
    boolean deletePhoto(Integer photoId) throws NullPhotoIdException, InvalidPhotoIdException;
}
