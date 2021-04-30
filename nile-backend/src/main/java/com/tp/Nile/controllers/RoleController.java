package com.tp.Nile.controllers;

import com.tp.Nile.exceptions.*;
import com.tp.Nile.models.Role;
import com.tp.Nile.models.User;
import com.tp.Nile.payload.UserIdentityAvailability;
import com.tp.Nile.payload.UserSummary;
import com.tp.Nile.repositories.RoleRepository;
import com.tp.Nile.repositories.UserRepository;
import com.tp.Nile.security.CurrentUser;
import com.tp.Nile.security.UserPrincipal;
import com.tp.Nile.services.RoleServiceImpl;
import com.tp.Nile.services.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/roles")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RoleController {

    @Autowired
    RoleServiceImpl service;

    @Autowired
    private RoleRepository roleRepository;

    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);



    @PostMapping()
    public ResponseEntity addRole(@RequestBody Role role) {
        try {
            return ResponseEntity.ok(service.addRole(role));
        }
        catch (NullRoleException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity getRoles() {
        try {
            return ResponseEntity.ok(service.getAllRoles());
        }
        catch (NullRoleIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{roleId}")
    public ResponseEntity getRoleById(@PathVariable Long roleId) {
        try {
            return ResponseEntity.ok(service.getRoleById(roleId));
        }
        catch (NullRoleIdException | InvalidRoleIdException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping()
    public ResponseEntity updateRole(@RequestBody Role role) {
        try {
            return ResponseEntity.ok(service.updateRole(role));
        }
        catch (NullRoleIdException | InvalidRoleIdException | NullRoleException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{roleId}")
    public String deleteRole(@PathVariable Long roleId) {
        String toReturn="";
        try {
            if (service.deleteRole(roleId)) {
                toReturn ="Role " + roleId + " deleted";
            }else{
                toReturn="Role " + roleId + " not found";
            }
        }catch (NullRoleIdException | InvalidRoleIdException ex){
            ex.getMessage();
        }
        return toReturn;
    }

}
