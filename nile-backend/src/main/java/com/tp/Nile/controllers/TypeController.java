package com.tp.Nile.controllers;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Category;
import com.tp.Nile.models.ProductPhoto;
import com.tp.Nile.models.Type;
import com.tp.Nile.models.Vendor;
import com.tp.Nile.services.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        try {
            return ResponseEntity.ok(service.addType(type));
        } catch (NullTypeNameException | EmptyTypeNameException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
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

    @PutMapping("/{typeId}")
    public ResponseEntity updateType(@PathVariable Integer typeId, @RequestBody Type updatedType) {
        try {
            return ResponseEntity.ok(service.updateType(typeId, updatedType));
        } catch (NullTypeIdException | InvalidTypeIdException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{typeId}")
    public ResponseEntity deleteType(@PathVariable Integer typeId) {
        try {
            if (service.deleteType(typeId)) {
                return ResponseEntity.ok("Type " + typeId + " successfully deleted");
            } else {
                return ResponseEntity.ok("Type " + typeId + " not found");
            }
        } catch (NullTypeIdException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}

