package com.atc.opportunity_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atc.opportunity_management_system.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
    
}