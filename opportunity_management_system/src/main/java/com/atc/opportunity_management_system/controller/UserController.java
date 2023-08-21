package com.atc.opportunity_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atc.opportunity_management_system.entity.Industry;
import com.atc.opportunity_management_system.entity.User;
import com.atc.opportunity_management_system.service.UserService;


@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("{userId}")
    public ResponseEntity<String> updateUser(@PathVariable Long userId, @RequestBody User updateUser){
        if(userService.updateUser(userId, updateUser)){
            return ResponseEntity.ok("user updated successfully");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId){
        if(userService.deleteUser(userId)){
            return ResponseEntity.ok("user deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }
}
