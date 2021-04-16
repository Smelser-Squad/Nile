package com.tp.Nile.controllers;

import com.tp.Nile.exceptions.InvalidPhotoIdException;
import com.tp.Nile.exceptions.InvalidProductIdException;
import com.tp.Nile.exceptions.NullPhotoIdException;
import com.tp.Nile.exceptions.NullProductIdException;
import com.tp.Nile.models.ProductPhoto;
import com.tp.Nile.services.PhotoServiceImpl;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductPhotoController {

    @Autowired
    PhotoServiceImpl service;

    @GetMapping("/productPhotos/{productId}")
    public ResponseEntity getProductPhotos(@PathVariable Integer productId) {
        try {
            return ResponseEntity.ok(service.getPhotosByProduct(productId));
        } catch (NullProductIdException | InvalidProductIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/productPhotos")
    public ResponseEntity getAllPhotos() {return ResponseEntity.ok(service.getAllPhotos());}

    @PutMapping("/update/photos/{photoId}")
    public ResponseEntity updatePhoto(@PathVariable ProductPhoto photo) {
        return ResponseEntity.ok(service.updatePhoto(photo));
    }

    @DeleteMapping("/delete/photos/{photoId}")
    public String deletePhoto(@PathVariable Integer photoId) {
        String toReturn="";
        try {
            if (service.deletePhoto(photoId)) {
                toReturn = "Photo " + photoId + " deleted.";
            } else {
                toReturn = "Photo " + photoId + " not found.";
            }
        } catch (InvalidPhotoIdException | NullPhotoIdException e ) {
            e.getMessage();
        }
        return toReturn;
    }

    @PostMapping("/add/photo/{productId}")
    public ResponseEntity addPhoto(@RequestBody ProductPhoto photo, @PathVariable Integer productId) {
        return ResponseEntity.ok(service.addPhoto(photo, productId));
    }
}
