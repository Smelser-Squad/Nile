package com.tp.Nile.controllers;

import com.tp.Nile.exceptions.InvalidTypeIdException;
import com.tp.Nile.exceptions.InvalidVendorIdException;
import com.tp.Nile.exceptions.NullTypeIdException;
import com.tp.Nile.exceptions.NullVendorIdException;
import com.tp.Nile.models.Type;
import com.tp.Nile.models.Vendor;
import com.tp.Nile.services.VendorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class VendorController {

    @Autowired
    VendorServiceImpl service;

    @PostMapping("/addVendor")
    public ResponseEntity addVendor(@RequestBody Vendor vendor){
        return ResponseEntity.ok(service.addVendor(vendor));
    }
    @GetMapping("/vendors")
    public ResponseEntity getAllVendors(){
        return ResponseEntity.ok(service.getAllVendors());
    }

    @GetMapping("/vendors/{vendorId}")
    public ResponseEntity getVendorById(@PathVariable Integer vendorId) {
        try {
            return ResponseEntity.ok(service.getVendorById(vendorId));
        } catch (NullVendorIdException | InvalidVendorIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
