package com.atc.opportunity_management_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atc.opportunity_management_system.entity.User;
import com.atc.opportunity_management_system.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean updateUser(Long userId, User updateUser){
        User user = userRepository.findById(userId).get();
        if(user!=null){
            if(user.isActive()){
                updateUser.setUserId(userId);
                userRepository.save(updateUser);
                return true;
            }
        }
        return false;
    }


    public boolean deleteUser(Long userId){
        User user = userRepository.findById(userId).get();
        if(user!=null){
            if(user.isActive()){
                user.setActive(false);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }
}
