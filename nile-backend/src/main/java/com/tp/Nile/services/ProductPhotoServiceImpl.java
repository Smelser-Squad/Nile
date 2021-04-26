package com.tp.Nile.services;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Product;
import com.tp.Nile.models.ProductPhoto;
import com.tp.Nile.repositories.ProductPhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductPhotoServiceImpl implements ProductPhotoService {
    @Autowired
    ProductPhotoRepository repo;
    @Autowired
    ProductServiceImpl productService;
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
    public List<ProductPhoto> getPhotosByProduct(Integer productId) throws InvalidProductIdException
        {return repo.getPhotosByProduct(productId);}

    @Override
    public List<ProductPhoto> getPhotosByProductColor(Integer productId, String color) throws InvalidProductIdException, NullProductIdException {
        return repo.getPhotosByProductColor(productId, color);
    }

    @Override
    public List<String> getColorsOfProduct(Integer productId) throws InvalidProductIdException {
        return repo.getColorsOfProduct(productId);
    }

    @Override
    public ProductPhoto addPhoto(ProductPhoto newPhoto, Integer productId) throws InvalidProductIdException {
        Product toAssociate = productService.getProductById(productId);
        newPhoto.setProduct(toAssociate);
        return repo.saveAndFlush(newPhoto);}

    @Override
    public ProductPhoto updatePhoto(ProductPhoto photo) {
        ProductPhoto updated = repo.findById(photo.getPhotoId()).get();

        if (updated != null) {
            updated.setColor(photo.getColor());
            updated.setImageSrc(photo.getImageSrc());
            updated.setProduct(photo.getProduct());
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
