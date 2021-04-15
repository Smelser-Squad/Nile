package com.tp.Nile.controllers;


import com.tp.Nile.exceptions.InvalidFeatureIdException;
import com.tp.Nile.exceptions.NullFeatureIdException;
import com.tp.Nile.models.Feature;
import com.tp.Nile.services.FeatureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(service.addFeature(feature));
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
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity updateFeature(@RequestBody Feature updatedFeature){
        return ResponseEntity.ok(service.updateFeature(updatedFeature));
    }
    @DeleteMapping("/{featureId}")
    public String deleteFeature(@PathVariable Integer featureId){
        {
            String toReturn="";
            try {
                if (service.deleteFeature(featureId)) {
                    toReturn ="Feature " + featureId + "deleted";
                }else{
                    toReturn="Feature " + featureId + "not found";
                }
            }catch (InvalidFeatureIdException | NullFeatureIdException ex){
                ex.getMessage();
            }
            return  toReturn;
        }
    }
}
