package com.tp.Nile.controllers;


import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Feature;
import com.tp.Nile.services.FeatureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/features")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FeatureController {

    @Autowired
    FeatureServiceImpl service;

    @PostMapping()
    public ResponseEntity addFeature(@RequestBody Feature feature){
        try {
            return new ResponseEntity<>(service.addFeature(feature), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping()
    public ResponseEntity getFeatures(){
        return ResponseEntity.ok(service.getAllFeatures());
    }

    @GetMapping("/{featureId}")
    public ResponseEntity getFeatureById(@PathVariable Integer featureId) {
        try {
            return ResponseEntity.ok(service.getFeatureById(featureId));
        } catch (NullFeatureIdException | InvalidFeatureIdException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/byproduct/{productId}")
    public ResponseEntity getFeaturesByProductId(@PathVariable Integer productId) {
        try {
            return ResponseEntity.ok(service.getFeaturesByProductId(productId));
        } catch (NullProductIdException | InvalidProductIdException | NullProductObjectException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity updateFeature(@RequestBody Feature updatedFeature){
        return ResponseEntity.ok(service.updateFeature(updatedFeature));
    }
  
    @DeleteMapping("/{featureId}")
    public ResponseEntity deleteFeature(@PathVariable Integer featureId){
        try {
            service.deleteFeature(featureId);
            return new ResponseEntity<>("Feature " + featureId + " deleted", HttpStatus.NO_CONTENT);
        } catch (NullFeatureIdException | InvalidFeatureIdException ex) {
            return new ResponseEntity<>("Feature " + featureId + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
