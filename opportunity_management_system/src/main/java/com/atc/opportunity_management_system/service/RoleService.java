package com.atc.opportunity_management_system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.atc.opportunity_management_system.entity.ErrorMessage;
import com.atc.opportunity_management_system.entity.Role;
import com.atc.opportunity_management_system.repository.RoleRepository;

@Service
public class RoleService {
    
    @Autowired
    RoleRepository roleRepository;

    //method to get all roles
    public ResponseEntity<Object> getAllRoles()
    {   
        //get if user is admin or not
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = loggedInUser.getAuthorities().stream().anyMatch(auth->auth.getAuthority().equals("ROLE_ADMIN"));

        if(isAdmin)
        {   
            //get all roles
            List<Role> roles = roleRepository.findAll();

            //throw message if there are no roles
            if(roles.isEmpty())
            {
                return new ResponseEntity<Object>(new ErrorMessage("No Roles Present",HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);               
            }
            
            //return all roles
            return ResponseEntity.ok(roles);
        }

        return new ResponseEntity<Object>(new ErrorMessage("You are not an admin",HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }
    
}
