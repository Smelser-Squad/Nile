package com.tp.Nile.controllers;


import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.User;
import com.tp.Nile.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;

@RequestMapping("/api/users")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserServiceImpl service;

    @PostMapping()
    public ResponseEntity addUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(service.addUser(user));
        }
        catch (NullUserIdException | InvalidUserIdException | NullUserException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity getUsers() {
        try {
            return ResponseEntity.ok(service.getAllUsers());
        }
        catch (InvalidUserIdException | NullUserException | NullUserIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity getUserById(@PathVariable Integer userId) {
        try {
            return ResponseEntity.ok(service.getUserById(userId));
        }
        catch (InvalidUserIdException | NullUserException | NullUserIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/answers/{userId}")
    public ResponseEntity getAnswersByUserId(@PathVariable Integer userId) {
        try {
            return ResponseEntity.ok(service.getAnswersByUserId(userId));
        }
        catch (InvalidUserIdException | NullUserException | NullUserIdException |
                NullAnswerIdException | NullAnswerException | InvalidAnswerIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/orders/{userId}")
    public ResponseEntity getOrdersByUserId(@PathVariable Integer userId) {
        try {
            return ResponseEntity.ok(service.getCartByUserId(userId));
        }
        catch (InvalidUserIdException | NullUserException | NullUserIdException |
                NullCartException | NullCartIdException | InvalidCartIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity updateUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(service.updateUser(user));
        }
        catch (InvalidUserIdException | NullUserException | NullUserIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable Integer userId) {
        String toReturn="";
        try {
            if (service.deleteUser(userId)) {
                toReturn ="Review " + userId + " deleted";
            }else{
                toReturn="Review " + userId + " not found";
            }
        }catch (InvalidUserIdException | NullUserIdException | NullUserException ex){
            ex.getMessage();
        }
        return toReturn;
    }

}
