package com.tp.Nile.controllers;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.FeatureRating;
import com.tp.Nile.services.FeatureRatingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/featureRating")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FeatureRatingController {

    @Autowired
    FeatureRatingServiceImpl service;

    @PostMapping
    public ResponseEntity addFeatureRating(@RequestBody FeatureRating rating) {
        try {
            return ResponseEntity.ok(service.addFeatureRating(rating));
        } catch (NullRatingIdException | NullFeatureRatingException | InvalidRatingIdException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getFeatureRatings() {
        try {
            return ResponseEntity.ok(service.getFeatureRatings());
        } catch (NullRatingIdException | NullFeatureRatingException | InvalidRatingIdException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/byId/{productId}/{featureId}")
    public ResponseEntity getRatingByFeatureAndProductId(@PathVariable Integer productId,
                                                         @PathVariable Integer featureId) {
        try {
            return ResponseEntity.ok(service.getRatingByFeatureAndProductId(productId, featureId));
        } catch (NullFeatureIdException | InvalidFeatureIdException
                | NullProductIdException | InvalidProductIdException
                | NullRatingIdException | InvalidRatingIdException ex)
        {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity updateFeatureRating(@RequestBody FeatureRating rating) {
        try {
            return ResponseEntity.ok(service.updateFeatureRating(rating));
        } catch (NullRatingIdException | NullFeatureRatingException | InvalidRatingIdException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{ratingId}")
    public String deleteFeatureRating(@PathVariable Integer ratingId) {
        String toReturn="";
        try {
            if (service.deleteFeatureRatingById(ratingId)) {
                toReturn ="Feature rating " + ratingId + " deleted";
            }else{
                toReturn="Feature rating " + ratingId + " not found";
            }
        }catch (InvalidRatingIdException | NullRatingIdException | NullFeatureRatingException ex){
            ex.getMessage();
        }
        return toReturn;
    }


}




