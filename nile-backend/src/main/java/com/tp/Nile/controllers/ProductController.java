package com.tp.Nile.controllers;


import com.tp.Nile.exceptions.InvaildProductIdException;
import com.tp.Nile.exceptions.NullProductIdException;
import com.tp.Nile.models.Category;
import com.tp.Nile.models.Product;
import com.tp.Nile.models.Type;
import com.tp.Nile.models.Vendor;
import com.tp.Nile.services.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    @Autowired
    ProductServiceImpl service;

    @PostMapping("/addProduct")
    public ResponseEntity addProduct(@RequestBody Product product){
        return ResponseEntity.ok(service.addProduct(product));
    }
    @GetMapping("/products")
    public ResponseEntity getProducts(){
        return ResponseEntity.ok(service.getAllProducts());
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity getProductById(@PathVariable Integer productId) {
        try {
            return ResponseEntity.ok(service.getProductById(productId));
        } catch (NullProductIdException | InvaildProductIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/products/category/{categoryId}")
    public ResponseEntity getProductByCategory(@PathVariable Category categoryId){
        return ResponseEntity.ok(service.getProductsByCategory(categoryId));
    }
    @GetMapping("/products/brand/{brand}")
    public ResponseEntity getProductByBrand(@PathVariable String brand){
        return ResponseEntity.ok(service.getProductByBrand(brand));
    }
    @GetMapping("/products/type/{type}")
    public ResponseEntity getProductByType(@PathVariable Type type){
        return ResponseEntity.ok(service.getProductsByType(type));
    }
    @GetMapping("/products/vendor/{vendorId}")
    public ResponseEntity getProductByVendor(@PathVariable Vendor vendorId){
        return ResponseEntity.ok(service.getProductsByVendor(vendorId));
    }
    @PutMapping("/update")
    public ResponseEntity updateProduct(@RequestBody Product updateProduct){
        return ResponseEntity.ok(service.updateProduct(updateProduct));
    }
    @DeleteMapping("delete/product/{productId}")
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
