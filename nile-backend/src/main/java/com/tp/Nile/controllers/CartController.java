package com.tp.Nile.controllers;


import com.tp.Nile.exceptions.InvalidCartIdException;
import com.tp.Nile.exceptions.InvalidCategoryIdException;
import com.tp.Nile.exceptions.NullCartIdException;
import com.tp.Nile.exceptions.NullCategoryIdException;
import com.tp.Nile.models.Cart;
import com.tp.Nile.services.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/carts")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

    @Autowired
    CartServiceImpl service;


    @PostMapping()
    public ResponseEntity addCart(@RequestBody Cart cart){

        try {
            return new ResponseEntity<>(service.addCart(cart), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping()
    public ResponseEntity getCarts(){
        return ResponseEntity.ok(service.getAllCarts());
    }

    @GetMapping("/{cartId}")
    public ResponseEntity getCartById(@PathVariable Integer cartId) {
        try {
            return ResponseEntity.ok(service.getCartById(cartId));
        } catch (InvalidCartIdException | NullCartIdException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping()
    public ResponseEntity updateCart(@RequestBody Cart updatedCart){
        return ResponseEntity.ok(service.updateCart(updatedCart));
    }
  
    @DeleteMapping("/{cartId}")
    public ResponseEntity deleteCart(@PathVariable Integer cartId){
        try {
            service.deleteCart(cartId);
            return new ResponseEntity<>("Cart " + cartId + " deleted", HttpStatus.NO_CONTENT);
        } catch (NullCartIdException | InvalidCartIdException ex) {
            return new ResponseEntity<>("Cart " + cartId + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
