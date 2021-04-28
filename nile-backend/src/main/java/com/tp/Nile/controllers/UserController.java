package com.tp.Nile.controllers;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.User;
import com.tp.Nile.payload.UserIdentityAvailability;
import com.tp.Nile.payload.UserSummary;
import com.tp.Nile.repositories.UserRepository;
import com.tp.Nile.security.CurrentUser;
import com.tp.Nile.security.UserPrincipal;
import com.tp.Nile.services.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    UserServiceImpl service;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(com.tp.Nile.controllers.UserController.class);

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return userSummary;
    }

    @GetMapping("/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @GetMapping("/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

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
