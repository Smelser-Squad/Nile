package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.ProductPhoto;
import com.tp.Nile.repositories.ProductPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoServiceImpl implements PhotoService{
    @Autowired
    ProductPhotoRepository repo;

    @Override
    public List<ProductPhoto> getAllPhotos() {
        return repo.findAll();
    }

    @Override
    public ProductPhoto getPhotoById(Integer photoId) throws NullPhotoIdException, InvalidPhotoIdException {
        if(photoId == null){
            throw new NullPhotoIdException("Cannot get photo with null id");
        }
        ProductPhoto retrieved = null;
        Optional<ProductPhoto> photo = repo.findById(photoId);
        if(photo.isPresent()){
            retrieved=photo.get();
            return retrieved;
        }else{
            throw new InvalidPhotoIdException("Photo with id " + photoId + " does not exist");
        }
    }

    @Override
    public List<ProductPhoto> getPhotosByProduct(Integer productId) throws InvalidProductIdException, NullProductIdException
        {return repo.getPhotosByProduct(productId);}

    @Override
    public ProductPhoto addPhoto(ProductPhoto newPhoto) {return repo.saveAndFlush(newPhoto);}

    @Override
    public ProductPhoto updatePhoto(ProductPhoto photo) {
        ProductPhoto updated = repo.findById(photo.getPhotoId()).get();

        if (updated != null) {
            updated.setColor(photo.getColor());
            updated.setImageSrc(photo.getImageSrc());
            updated.setProductId(photo.getProductId());
        }
        return repo.saveAndFlush(photo);
    }

    @Override
    public boolean deletePhoto(Integer photoId) throws NullPhotoIdException, InvalidPhotoIdException {
        if (photoId == null) {
            throw new NullPhotoIdException("Can not delete photo with an ID of null.");
        }
        ProductPhoto retrieved = repo.findById(photoId).get();
        if (retrieved != null){
            repo.delete(retrieved);
            return true;
        } else {
            throw new InvalidPhotoIdException("Photo with that ID does not exist.");
        }
    }






}
