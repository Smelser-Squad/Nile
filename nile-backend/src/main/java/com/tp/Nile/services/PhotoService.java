package com.tp.Nile.services;


import com.tp.Nile.exceptions.InvalidProductIdException;
import com.tp.Nile.exceptions.InvalidPhotoIdException;
import com.tp.Nile.exceptions.NullPhotoIdException;
import com.tp.Nile.exceptions.NullProductIdException;
import com.tp.Nile.models.ProductPhoto;

import java.util.List;

public interface PhotoService {
    List<ProductPhoto> getAllPhotos();

    ProductPhoto getPhotoById(Integer photoId) throws NullPhotoIdException, InvalidPhotoIdException;

    List<ProductPhoto> getPhotosByProduct(Integer productId) throws InvalidProductIdException, NullProductIdException;

    ProductPhoto addPhoto(ProductPhoto newPhoto, Integer productId) throws InvalidProductIdException;

    ProductPhoto updatePhoto(ProductPhoto update);

    boolean deletePhoto(Integer photoId) throws NullPhotoIdException, InvalidPhotoIdException;

    List<ProductPhoto> getPhotosByProductColor(Integer productId, String color) throws InvalidProductIdException, NullProductIdException;
}
