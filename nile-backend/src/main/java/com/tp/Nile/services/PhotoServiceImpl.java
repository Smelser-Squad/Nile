package com.tp.Nile.services;

import com.tp.Nile.exceptions.InvalidOrderIdException;
import com.tp.Nile.exceptions.InvalidPhotoIdException;
import com.tp.Nile.exceptions.NullOrderIdException;
import com.tp.Nile.exceptions.NullPhotoIdException;
import com.tp.Nile.models.Order;
import com.tp.Nile.models.Product;
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
    public List<ProductPhoto> getPhotosByProduct(Product product) {return repo.getPhotosByProduct(product);}

    @Override
    public ProductPhoto addPhoto(ProductPhoto newPhoto) {return repo.saveAndFlush(newPhoto);}

    @Override
    public ProductPhoto updatePhoto(ProductPhoto update) {return repo.saveAndFlush(update);}
}
