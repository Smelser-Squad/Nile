package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidFeatureIdException;
import com.tp.Nile.exceptions.InvalidProductIdException;
import com.tp.Nile.exceptions.NullFeatureIdException;
import com.tp.Nile.models.Feature;
import com.tp.Nile.models.Product;
import com.tp.Nile.repositories.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FeatureServiceImpl implements FeatureService {

    @Autowired
    FeatureRepository repo;

    @Autowired
    ProductServiceImpl productService;

    @Override
    public List<Feature> getAllFeatures() {
        return repo.findAll();
    }

    @Override
    public Feature getFeatureById(Integer featureId) throws NullFeatureIdException, InvalidFeatureIdException {
        if(featureId == null){
            throw new NullFeatureIdException("Cannot get feature with null id");
        }
        Feature retrieved = null;
        Optional<Feature> feature = repo.findById(featureId);
        if(feature.isPresent()){
            retrieved=feature.get();
            return retrieved;
        }else{
            throw new InvalidFeatureIdException("Feature with that id does not exist");
        }
    }

    @Override
    public Feature addFeature(Feature newFeature) {

        Set<Product>features=new HashSet<>();


            newFeature.setProducts(features);


        return repo.saveAndFlush(newFeature);
    }

    @Override
    public Feature updateFeature(Feature updatedFeature) {
        return repo.saveAndFlush(updatedFeature);
    }

    @Override
    public boolean deleteFeature(Integer featureId) throws NullFeatureIdException, InvalidFeatureIdException {
        if(featureId == null){
            throw new NullFeatureIdException("Cannot delete feature with null id");
        }
        Feature retrieved = repo.findById(featureId).get();
        if(retrieved != null){
            repo.delete(retrieved);
            return true;
        }else{
            throw new InvalidFeatureIdException("Feature with that id does not exist");
        }
    }
}
