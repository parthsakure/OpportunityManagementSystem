package com.atc.opportunity_management_system.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Optional<User>userquerry = userRepository.findByEmail(userEmail);
        User user = null;
        if(!userquerry.isPresent()){
            user = new User();
            user.setUsername(userEmail.split("@")[0]);
            user.setFirstName(name[0]);
            user.setLastName(name.length > 1 ? name[1] : "");
            user.setEmail(userEmail);
            user.setBbdBucks(0);
            user.setActive(true);
            user.setRole(roleRepository.findByRole("ROLE_USER").get());
            userRepository.save(user);
        }
        else{
            user = userquerry.get();
        }
        SecurityContextHolder.getContext().setAuthentication(
            new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
            )
        );
    }

    public Object getAuthUser() {
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        return user;
    }
}
