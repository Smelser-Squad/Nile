package com.tp.Nile.controllers;


import com.tp.Nile.exceptions.InvalidCategoryIdException;
import com.tp.Nile.exceptions.InvalidProductIdException;
import com.tp.Nile.exceptions.NullCategoryIdException;
import com.tp.Nile.models.Category;
import com.tp.Nile.services.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/categories")
@RestController
@CrossOrigin
public class CategoryController {

    @Autowired
    CategoryServiceImpl service;


    @PostMapping()
    public ResponseEntity addCategory(@RequestBody Category category){
        try {
            return new ResponseEntity<>(service.addCategory(category), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping()
    public ResponseEntity getCategories(){
        return ResponseEntity.ok(service.getAllCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity getCategoryById(@PathVariable Integer categoryId) {
        try {
            return ResponseEntity.ok(service.getCategoryById(categoryId));
        } catch (InvalidCategoryIdException | NullCategoryIdException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping()
    public ResponseEntity updateCategory(@RequestBody Category updatedCategory){
        return ResponseEntity.ok(service.updateCategory(updatedCategory));
    }
  
    @DeleteMapping("/{categoryId}")
    public ResponseEntity deleteCategory(@PathVariable Integer categoryId){
        try {
            service.deleteCategory(categoryId);
            return new ResponseEntity<>("Category " + categoryId + " deleted", HttpStatus.NO_CONTENT);
        } catch (NullCategoryIdException | InvalidCategoryIdException ex) {
            return new ResponseEntity<>("Category " + categoryId + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
