package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.FeatureRating;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FeatureRatingService {

    List<FeatureRating> getFeatureRatings() throws NullRatingIdException,
            NullFeatureRatingException,  InvalidRatingIdException;

    FeatureRating addFeatureRating(FeatureRating rating) throws NullRatingIdException,
            NullFeatureRatingException, InvalidRatingIdException;

    Integer getRatingByFeatureAndProductId(Integer productId, Integer featureId)
        throws NullFeatureIdException, InvalidFeatureIdException,
            NullProductIdException,InvalidProductIdException,
            NullRatingIdException, InvalidRatingIdException;

    FeatureRating updateFeatureRating(FeatureRating rating) throws NullRatingIdException,
            NullFeatureRatingException, InvalidRatingIdException;

    boolean deleteFeatureRatingById(Integer ratingId) throws
            InvalidRatingIdException, NullRatingIdException, NullFeatureRatingException;
}
