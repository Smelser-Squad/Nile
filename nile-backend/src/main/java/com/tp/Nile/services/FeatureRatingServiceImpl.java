package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Feature;
import com.tp.Nile.models.FeatureRating;
import com.tp.Nile.repositories.FeatureRatingRepository;
import com.tp.Nile.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeatureRatingServiceImpl implements FeatureRatingService {

    @Autowired
    FeatureRatingRepository repo;

    @Override
    public List<FeatureRating> getFeatureRatings() throws NullRatingIdException, NullFeatureRatingException, InvalidRatingIdException {
        return repo.findAll();
    }

    @Override
    public FeatureRating addFeatureRating(FeatureRating rating) throws NullRatingIdException, NullFeatureRatingException, InvalidRatingIdException {
        if (rating.getRatingId() == null)
            throw new NullRatingIdException("Rating id can not be null");
        else if (rating.getRatingId() < 0)
            throw new InvalidRatingIdException("Rating id is invalid");
        else if (rating.getFeature() == null || rating.getProduct() == null || rating.getRating() == null)
            throw new NullFeatureRatingException("There is a null value in the object");
        else
            return repo.saveAndFlush(rating);
    }

    @Override
    public Integer getRatingByFeatureAndProductId(Integer productId, Integer featureId) throws NullFeatureIdException,
            InvalidFeatureIdException, NullProductIdException, InvalidProductIdException,
            NullRatingIdException, InvalidRatingIdException {
        if (featureId == null)
            throw new NullFeatureIdException("Feature id can not be null");
        if (productId == null)
            throw new NullProductIdException("Product id can not be null");

        Integer rating = null;

        Optional<Integer> featureRating = repo.getRatingByFeatureAndProductId(featureId, productId);

        if (featureRating != null)
        {
            rating = featureRating.get();
            return rating;
        }
        else {
            throw new InvalidProductIdException("Feature or product id was invalid");
        }

    }

    @Override
    public FeatureRating updateFeatureRating(FeatureRating rating) throws NullRatingIdException, NullFeatureRatingException, InvalidRatingIdException {
        return repo.saveAndFlush(rating);
    }

    @Override
    public boolean deleteFeatureRatingById(Integer ratingId) throws InvalidRatingIdException, NullRatingIdException, NullFeatureRatingException {
        if(ratingId == null){
            throw new NullRatingIdException("Cannot delete rating with null id");
        }
        FeatureRating retrieved = repo.findById(ratingId).get();
        if(retrieved != null){
            repo.delete(retrieved);
            return true;
        }else{
            throw new InvalidRatingIdException("Rating with that id does not exist");
        }
    }
}
