package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Feature;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeatureService {

    List<Feature> getAllFeatures();
    Feature getFeatureById(Integer featureId) throws NullFeatureIdException, InvalidFeatureIdException;
    Feature addFeature(Feature newFeature);
    Feature updateFeature(Feature updatedFeature);
    boolean deleteFeature(Integer featureId) throws NullFeatureIdException, InvalidFeatureIdException;
    List<Feature> getFeaturesByProductId(Integer productId) throws NullProductIdException, NullProductObjectException,
            InvalidProductIdException;


}
