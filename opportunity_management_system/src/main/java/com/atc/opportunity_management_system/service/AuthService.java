package com.atc.opportunity_management_system.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atc.opportunity_management_system.entity.User;
import com.atc.opportunity_management_system.repository.RoleRepository;
import com.atc.opportunity_management_system.repository.UserRepository;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public void authoriseUser(Map<String, Object> userData) {
        String userEmail = userData.get("email").toString();
        String[] name = userData.get("name").toString().split(" ");
        User user = userRepository.findByEmail(userEmail);
        if(user==null){
            // System.out.println("*********************User NOT FOUND");
            user = new User();
            user.setUsername(userEmail.split("@")[0]);
            user.setFirstName(name[0]);
            user.setLastName(name[0]);
            user.setEmail(userEmail);
            user.setBbdBucks(0);
            user.setActive(true);
            user.setRole(roleRepository.findByRole("ROLE_USER").get());
            
            userRepository.save(user);
            // System.out.println("*********************User Created!!");
        }
        else{
            // System.out.println("*********************User FOUND");
        }

    }
}
