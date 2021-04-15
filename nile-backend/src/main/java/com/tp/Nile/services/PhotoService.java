package com.tp.Nile.services;


import com.tp.Nile.exceptions.InvalidPhotoIdException;
import com.tp.Nile.exceptions.NullPhotoIdException;
import com.tp.Nile.models.ProductPhoto;

import java.util.List;

public interface PhotoService {
    List<ProductPhoto> getAllPhotos();
    ProductPhoto getPhotoById(Integer photoId) throws NullPhotoIdException, InvalidPhotoIdException;
}