package com.tp.Nile.controllers;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.ProductSpecification;
import com.tp.Nile.services.ProductSpecificationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/productspecs")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductSpecificationController {

    @Autowired
    ProductSpecificationServiceImpl service;

    @PostMapping
    public ResponseEntity addProductSpec(@RequestBody ProductSpecification productSpec) {
        try {
            return ResponseEntity.ok(service.addProductSpec(productSpec));
        } catch (NullProductIdException | InvalidProductIdException | NullSpecIdException | InvalidSpecIdException | NullProductSpecIdObjectException | NullSpecValueException | InvalidSpecValueException | TypesDoNotMatchException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAllProductSpecs() {
        return ResponseEntity.ok(service.getAllProductSpecs());
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity getProductSpecsByProducts(@PathVariable Integer productId) {
        return ResponseEntity.ok(service.getProductSpecsByProduct(productId));
    }

    @GetMapping("/spec/{specId}")
    public ResponseEntity getProductSpecsBySpec(@PathVariable Integer specId) {
        return ResponseEntity.ok(service.getProductSpecsBySpec(specId));
    }

    @GetMapping("/product/{productId}/spec/{specId}")
    public ResponseEntity getProductSpecsById(@PathVariable Integer productId, @PathVariable Integer specId) {
        try {
            return ResponseEntity.ok(service.getProductSpecById(productId, specId));
        } catch (NullProductIdException | NullSpecIdException | InvalidProductSpecIdException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity updateProductSpec(@RequestBody ProductSpecification updatedProductSpec) {
        try {
            return ResponseEntity.ok(service.updateProductSpec(updatedProductSpec));
        } catch (NullProductIdException | NullProductSpecIdObjectException | NullSpecValueException | InvalidProductIdException | InvalidSpecValueException | InvalidSpecIdException | NullSpecIdException | InvalidProductSpecIdException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/product/{productId}/spec/{specId}")
    public ResponseEntity deleteProductSpec(@PathVariable Integer productId, @PathVariable Integer specId) {
        try {
            service.deleteProductSpec(productId, specId);
            return ResponseEntity.ok("Spec " + specId + " has been removed from product " + productId);
        } catch (NullProductIdException | NullSpecIdException | InvalidProductSpecIdException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
