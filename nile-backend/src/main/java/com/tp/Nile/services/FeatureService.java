package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidFeatureIdException;
import com.tp.Nile.exceptions.NullFeatureIdException;
import com.tp.Nile.models.Feature;

import java.util.List;


public interface FeatureService {

    List<Feature> getAllFeatures();
    Feature getFeatureById(Integer featureId) throws NullFeatureIdException, InvalidFeatureIdException;
    Feature addFeature(Feature newFeature,Integer productId);
    Feature updateFeature(Feature updatedFeature);
    boolean deleteFeature(Integer featureId) throws NullFeatureIdException, InvalidFeatureIdException;



}
