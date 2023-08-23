package com.atc.opportunity_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.atc.opportunity_management_system.entity.User;
import com.atc.opportunity_management_system.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{userId}")
    private RedirectView getUser(@PathVariable Long userId){
        return new RedirectView("/users/"+userId);
    }

    @PutMapping("{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody @Valid User updateUser) {
        User user = userService.updateUser(userId, updateUser);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable  Long userId) {
        User user = userService.deleteUser(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }
}
