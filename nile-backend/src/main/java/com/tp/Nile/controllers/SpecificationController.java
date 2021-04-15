package com.tp.Nile.controllers;

import com.tp.Nile.exceptions.InvalidSpecIdException;
import com.tp.Nile.exceptions.NullSpecIdException;
import com.tp.Nile.models.Specification;
import com.tp.Nile.services.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/specs")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class SpecificationController {

    @Autowired
    SpecificationService service;

    @PostMapping()
    public ResponseEntity addSpec(@RequestBody Specification newSpec) {
        return ResponseEntity.ok(service.addSpec(newSpec));
    }

    @GetMapping()
    public ResponseEntity getSpecs() {
        return ResponseEntity.ok(service.getAllSpecs());
    }

    @GetMapping("/{specId}")
    public ResponseEntity getSpecById(@PathVariable Integer specId) {
        try {
            return ResponseEntity.ok(service.getSpecById(specId));
        } catch (NullSpecIdException | InvalidSpecIdException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/{specId}")
    public ResponseEntity updateSpec(@PathVariable Integer specId, @RequestBody Specification updatedSpec) {
        try {
            return ResponseEntity.ok(service.updateSpec(specId, updatedSpec));
        } catch (NullSpecIdException | InvalidSpecIdException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{specId}")
    public ResponseEntity deleteSpec(@PathVariable Integer specId) {
        try {
            if (service.deleteSpec(specId)) {
                return ResponseEntity.ok("Specification " + specId + " successfully deleted");
            } else {
                return ResponseEntity.ok("Specification " + specId + " not found");
            }
        } catch (NullSpecIdException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
