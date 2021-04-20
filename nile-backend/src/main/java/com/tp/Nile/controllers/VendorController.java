package com.tp.Nile.controllers;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Category;
import com.tp.Nile.models.Type;
import com.tp.Nile.models.Vendor;
import com.tp.Nile.services.VendorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/vendors")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class VendorController {

    @Autowired
    VendorServiceImpl service;

    @PostMapping()
    public ResponseEntity addVendor(@RequestBody Vendor vendor){
        return ResponseEntity.ok(service.addVendor(vendor));
    }
    @GetMapping()
    public ResponseEntity getAllVendors(){
        return ResponseEntity.ok(service.getAllVendors());
    }

    @GetMapping("/{vendorId}")
    public ResponseEntity getVendorById(@PathVariable Integer vendorId) {
        try {
            return ResponseEntity.ok(service.getVendorById(vendorId));
        } catch (NullVendorIdException | InvalidVendorIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping()
    public ResponseEntity updateVendor(@RequestBody Vendor updatedVendor){
        return ResponseEntity.ok(service.updateVendor(updatedVendor));
    }

    @DeleteMapping("/{vendorId}")
    public String deleteVendor(@PathVariable Integer vendorId){
        {
            String toReturn="";
            try {
                if (service.deleteVendor(vendorId)) {
                    toReturn ="Vendor " + vendorId + "deleted";
                }else{
                    toReturn="Vendor " + vendorId + "not found";
                }
            }catch (InvalidVendorIdException | NullVendorIdException ex){
                ex.getMessage();
            }
            return  toReturn;
        }
    }
}
