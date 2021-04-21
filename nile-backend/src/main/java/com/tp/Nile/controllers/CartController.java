package com.tp.Nile.controllers;


import com.tp.Nile.exceptions.InvalidCartIdException;
import com.tp.Nile.exceptions.NullCartIdException;
import com.tp.Nile.models.Cart;
import com.tp.Nile.services.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseEntity.ok(service.addCart(cart));
    }
    @GetMapping()
    public ResponseEntity getCarts(){
        return ResponseEntity.ok(service.getAllCarts());
    }

    @GetMapping("/{cartId}")
    public ResponseEntity getCartById(@PathVariable Integer cartId) {
        try {
            return ResponseEntity.ok(service.getCartById(cartId));
        } catch (NullCartIdException | InvalidCartIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity updateCart(@RequestBody Cart updatedCart){
        return ResponseEntity.ok(service.updateCart(updatedCart));
    }
  
    @DeleteMapping("/{cartId}")
    public String deleteCart(@PathVariable Integer cartId){
        {
            String toReturn="";
            try {
                if (service.deleteCart(cartId)) {
                    toReturn ="Cart " + cartId + "deleted";
                }else{
                    toReturn="Cart " + cartId + "not found";
                }
            }catch (InvalidCartIdException | NullCartIdException ex){
                ex.getMessage();
            }
            return  toReturn;
        }
    }
}
