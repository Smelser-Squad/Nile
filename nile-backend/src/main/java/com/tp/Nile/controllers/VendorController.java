package com.tp.Nile.controllers;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Category;
import com.tp.Nile.models.Type;
import com.tp.Nile.models.Vendor;
import com.tp.Nile.services.VendorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/vendors")
@RestController
@CrossOrigin
public class VendorController {

    @Autowired
    VendorServiceImpl service;

    @PostMapping()
    public ResponseEntity addVendor(@RequestBody Vendor vendor){
        try {
            return new ResponseEntity<>(service.addVendor(vendor), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping()
    public ResponseEntity getVendors(){
        return ResponseEntity.ok(service.getAllVendors());
    }

    @GetMapping("/{vendorId}")
    public ResponseEntity getVendorById(@PathVariable Integer vendorId) {
        try {
            return ResponseEntity.ok(service.getVendorById(vendorId));
        } catch (InvalidVendorIdException | NullVendorIdException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping()
    public ResponseEntity updateVendor(@RequestBody Vendor updatedVendor){
        return ResponseEntity.ok(service.updateVendor(updatedVendor));
    }

    @DeleteMapping("/{vendorId}")
    public ResponseEntity deleteVendor(@PathVariable Integer vendorId){
        try {
            service.deleteVendor(vendorId);
            return new ResponseEntity<>("Vendor " + vendorId + " deleted", HttpStatus.NO_CONTENT);
        } catch (NullVendorIdException | InvalidVendorIdException ex) {
            return new ResponseEntity<>("Vendor " + vendorId + " not found", HttpStatus.NOT_FOUND);
        }
    }

}
