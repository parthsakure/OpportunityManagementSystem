package com.atc.opportunity_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.atc.opportunity_management_system.entity.User;
import com.atc.opportunity_management_system.repository.UserRepository;
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User updateUser(Long userId, User updateUser) {
        User user = userRepository.findById(userId).get();
        if (user != null) {
            if (user.isActive()) {
                updateUser.setUserId(userId);
                return userRepository.save(updateUser);
            }
        }
        return null;
    }

    public User deleteUser(Long userId) {
        User user = userRepository.findById(userId).get();
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                return userRepository.save(user);
            }
        }
        return null;
    }

    public ResponseEntity<Object> getAllUsers(boolean active) {
        if(active){
            return ResponseEntity.ok(userRepository.findByActive(active));
        }
        return ResponseEntity.ok(userRepository.findAll());
    }
}
