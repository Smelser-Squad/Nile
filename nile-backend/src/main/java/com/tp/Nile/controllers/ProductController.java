package com.tp.Nile.controllers;

import com.tp.Nile.controllers.requests.AddProductRequest;
import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Category;
import com.tp.Nile.models.Product;
import com.tp.Nile.models.Type;
import com.tp.Nile.models.Vendor;
import com.tp.Nile.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/products")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    ProductServiceImpl service;

    @PostMapping()
    public ResponseEntity addProduct(@RequestBody AddProductRequest product) {
        try {
            return ResponseEntity.ok(service.addProduct(product));
        } catch (NullProductObjectException | NullBrandException | NullNameException | NullDescriptionException | NullPriceException | InvalidPriceException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping()
    public ResponseEntity getProducts(){
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity getProductById(@PathVariable Integer productId) {
        try {
            return ResponseEntity.ok(service.getProductById(productId));
        } catch (NullProductIdException | InvaildProductIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/category")
    public ResponseEntity getProductByCategory(@RequestBody Category category){
        return ResponseEntity.ok(service.getProductsByCategory(category));
    }
    @GetMapping("/brand")
    public ResponseEntity getProductByBrand(@RequestBody String brand){
        return ResponseEntity.ok(service.getProductByBrand(brand));
    }
    @GetMapping("/type")
    public ResponseEntity getProductByType(@RequestBody Type type){
        return ResponseEntity.ok(service.getProductsByType(type));
    }
    @GetMapping("/vendor")
    public ResponseEntity getProductByVendor(@RequestBody Vendor vendor){
        return ResponseEntity.ok(service.getProductsByVendor(vendor));
    }
    @PutMapping()
    public ResponseEntity updateProduct(@RequestBody Product updateProduct){
        return ResponseEntity.ok(service.updateProduct(updateProduct));
    }
    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Integer productId){
        {
            String toReturn="";
            try {
                if (service.deleteProduct(productId)) {
                    toReturn ="Product " + productId + "deleted";
                }else{
                    toReturn="Product " + productId + "not found";
                }
            }catch (InvaildProductIdException | NullProductIdException ex){
                ex.getMessage();
            }
            return  toReturn;
        }
    }
}
