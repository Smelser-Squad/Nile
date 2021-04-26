package com.tp.Nile.services;


import com.tp.Nile.exceptions.InvalidProductIdException;
import com.tp.Nile.exceptions.InvalidPhotoIdException;
import com.tp.Nile.exceptions.NullPhotoIdException;
import com.tp.Nile.exceptions.NullProductIdException;
import com.tp.Nile.models.ProductPhoto;

import java.util.List;

public interface ProductPhotoService {
    List<ProductPhoto> getAllPhotos();

    List<ProductPhoto> getPhotosByProductColor(Integer productId, String color) throws InvalidProductIdException, NullProductIdException;

    ProductPhoto getPhotoById(Integer photoId) throws NullPhotoIdException, InvalidPhotoIdException;
    List<ProductPhoto> getPhotosByProduct(Integer productId) throws InvalidProductIdException, NullProductIdException;

    List<String> getColorsOfProduct(Integer productId) throws InvalidProductIdException;

    ProductPhoto addPhoto(ProductPhoto newPhoto, Integer productId) throws InvalidProductIdException;
    ProductPhoto updatePhoto(ProductPhoto update);
<<<<<<< HEAD:nile-backend/src/main/java/com/tp/Nile/services/PhotoService.java

    List<String> getColorsOfProduct(Integer productId) throws InvalidProductIdException;

=======
>>>>>>> 9f26d696c5174afe4b7bb322042b579ba6533fce:nile-backend/src/main/java/com/tp/Nile/services/ProductPhotoService.java
    boolean deletePhoto(Integer photoId) throws NullPhotoIdException, InvalidPhotoIdException;

}
