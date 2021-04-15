package com.tp.Nile.controllers;


import com.tp.Nile.exceptions.InvalidCategoryIdException;
import com.tp.Nile.exceptions.NullCategoryIdException;
import com.tp.Nile.models.Category;
import com.tp.Nile.services.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/categories")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryController {

    @Autowired
    CategoryServiceImpl service;


    @PostMapping("/categories")
    public ResponseEntity addCategory(@RequestBody Category category){
        return ResponseEntity.ok(service.addCategory(category));
    }
    @GetMapping("/categories")
    public ResponseEntity getCategories(){
        return ResponseEntity.ok(service.getAllCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity getCategoryById(@PathVariable Integer categoryId) {
        try {
            return ResponseEntity.ok(service.getCategoryById(categoryId));
        } catch (NullCategoryIdException | InvalidCategoryIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/categories")
    public ResponseEntity updateCategory(@RequestBody Category updatedCategory){
        return ResponseEntity.ok(service.updateCategory(updatedCategory));
    }
  
    @DeleteMapping("/categories/{categoryId}")
    public String deleteCategory(@PathVariable Integer categoryId){
        {
            String toReturn="";
            try {
                if (service.deleteCategory(categoryId)) {
                    toReturn ="Category " + categoryId + "deleted";
                }else{
                    toReturn="Category " + categoryId + "not found";
                }
            }catch (InvalidCategoryIdException | NullCategoryIdException ex){
                ex.getMessage();
            }
            return  toReturn;
        }
    }
}
