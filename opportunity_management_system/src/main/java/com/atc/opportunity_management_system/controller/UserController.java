package com.atc.opportunity_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atc.opportunity_management_system.entity.ErrorMessage;
import com.atc.opportunity_management_system.entity.User;
import com.atc.opportunity_management_system.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable Long userId, @RequestBody @Valid User updateUser) {
        User user = userService.updateUser(userId, updateUser);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return new ResponseEntity<Object>(new ErrorMessage("User not found. Id: "+userId,HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable  Long userId) {
        User user = userService.deleteUser(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return new ResponseEntity<Object>(new ErrorMessage("User not found. Id: "+userId,HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllUsers(@RequestParam(required = false) boolean active){
        return userService.getAllUsers(active);
    }

    
    @GetMapping("/{id}")
    @PreAuthorize("ROLE_ADMIN")
    public ResponseEntity<Object> getUser(@PathVariable Long id){
        User user = userService.getUser(id);
        if(user==null){
            return new ResponseEntity<Object>(new ErrorMessage("User Not Found id: "+id, HttpStatus.NOT_FOUND.value()), null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);
    }


}
