package com.tp.Nile.controllers;

import com.tp.Nile.exceptions.InvalidPhotoIdException;
import com.tp.Nile.exceptions.InvalidProductIdException;
import com.tp.Nile.exceptions.NullPhotoIdException;
import com.tp.Nile.exceptions.NullProductIdException;
import com.tp.Nile.models.ProductPhoto;
import com.tp.Nile.services.ProductPhotoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/productPhotos")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductPhotoController {

    @Autowired
    ProductPhotoServiceImpl service;

    @GetMapping("/{productId}")
    public ResponseEntity getPhotosByProduct(@PathVariable Integer productId) {
        try {
            return ResponseEntity.ok(service.getPhotosByProduct(productId));
        } catch (InvalidProductIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/{productId}/{color}")
    public ResponseEntity getPhotosByProductColor(@PathVariable Integer productId, @PathVariable String color){
        try {
            return ResponseEntity.ok(service.getPhotosByProductColor(productId, color));
        } catch (InvalidProductIdException | NullProductIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/colors/{productId}")
    public ResponseEntity getColorsOfProduct(@PathVariable Integer productId){
        try {
            return ResponseEntity.ok(service.getColorsOfProduct(productId));
        }
        catch (InvalidProductIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAllPhotos() {return ResponseEntity.ok(service.getAllPhotos());}

    @PutMapping("/update/{photoId}")
    public ResponseEntity updatePhoto(@PathVariable ProductPhoto photo) {
        return ResponseEntity.ok(service.updatePhoto(photo));
    }

    @DeleteMapping("/delete/{photoId}")
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

    @PostMapping("/add/{productId}")
    public ResponseEntity addPhoto(@RequestBody ProductPhoto photo, @PathVariable Integer productId) throws InvalidProductIdException {
        return ResponseEntity.ok(service.addPhoto(photo, productId));
    }


    @GetMapping("/colors/{productId}")
    public ResponseEntity getColorsOfProduct(@PathVariable Integer productId){
        try {
            return ResponseEntity.ok(service.getColorsOfProduct(productId));
        }
        catch (InvalidProductIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
