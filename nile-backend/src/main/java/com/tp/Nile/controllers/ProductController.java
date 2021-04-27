package com.tp.Nile.controllers;

import com.tp.Nile.exceptions.InvalidProductIdException;
import com.tp.Nile.models.Product;
import com.tp.Nile.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/products")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    ProductServiceImpl service;

    @PostMapping
    public ResponseEntity addProduct(@RequestBody Product product) {
        try {
            return new ResponseEntity<>(service.addProduct(product), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getProducts(){
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity getProductById(@PathVariable Integer productId) {
        try {
            return ResponseEntity.ok(service.getProductById(productId));
        } catch (InvalidProductIdException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/category/{category}")
    public ResponseEntity getProductByCategory(@PathVariable String category){
        return ResponseEntity.ok(service.getProductsByCategory(category));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity getProductByType(@PathVariable String type){
        return ResponseEntity.ok(service.getProductsByType(type));
    }
    @GetMapping("/vendor/{vendor}")
    public ResponseEntity getProductByVendor(@PathVariable String vendor){
        return ResponseEntity.ok(service.getProductsByVendor(vendor));
    }

    @GetMapping("/brand/{brand}")
    public ResponseEntity getProductByBrand(@PathVariable String brand){
        return ResponseEntity.ok(service.getProductByBrand(brand));
    }

    @PutMapping
    public ResponseEntity updateProduct(@RequestBody Product updateProduct){
        return ResponseEntity.ok(service.updateProduct(updateProduct));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Integer productId) {
        try {
            service.deleteProduct(productId);
            return new ResponseEntity<>("Product " + productId + " deleted", HttpStatus.NO_CONTENT);
        } catch (InvalidProductIdException ex) {
            return new ResponseEntity<>("Product " + productId + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
