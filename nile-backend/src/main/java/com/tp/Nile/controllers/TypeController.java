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
@CrossOrigin
public class TypeController {

    @Autowired
    TypeServiceImpl service;

    @PostMapping()
    public ResponseEntity addType(@RequestBody Type type){
        try {
            return new ResponseEntity<>(service.addType(type), HttpStatus.CREATED);
        } catch (NullTypeNameException | EmptyTypeNameException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
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
        } catch (InvalidTypeIdException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (NullTypeIdException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity updateType(@RequestBody Type updatedType) {
        try {
            return ResponseEntity.ok(service.updateType(updatedType));
        } catch (NullTypeIdException | InvalidTypeIdException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{typeId}")
    public ResponseEntity deleteType(@PathVariable Integer typeId) {
        try {
            if(service.deleteType(typeId)) {
                return new ResponseEntity<>("Type " + typeId + " deleted", HttpStatus.NO_CONTENT);
            } else{
                return new ResponseEntity<>("Type " + typeId + " not found", HttpStatus.NOT_FOUND);
            }
        }
        catch (NullTypeIdException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}

