package com.tp.Nile.controllers;

import com.tp.Nile.exceptions.InvalidCategoryIdException;
import com.tp.Nile.exceptions.InvalidTypeIdException;
import com.tp.Nile.exceptions.NullCategoryIdException;
import com.tp.Nile.exceptions.NullTypeIdException;
import com.tp.Nile.models.Category;
import com.tp.Nile.models.ProductPhoto;
import com.tp.Nile.models.Type;
import com.tp.Nile.models.Vendor;
import com.tp.Nile.services.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.type.NullType;

@RequestMapping("/api/types")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class TypeController {

    @Autowired
    TypeServiceImpl service;

    @PostMapping()
    public ResponseEntity addType(@RequestBody Type type){
        return ResponseEntity.ok(service.addType(type));
    }

    @GetMapping()
    public ResponseEntity getAllTypes(){
        return ResponseEntity.ok(service.getAllTypes());
    }

    @GetMapping("/{typeId}")
    public ResponseEntity getTypeById(@PathVariable Integer typeId) {
        try {
            return ResponseEntity.ok(service.getTypeById(typeId));
        } catch (NullTypeIdException | InvalidTypeIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }



}

