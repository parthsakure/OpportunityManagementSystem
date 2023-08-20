package com.atc.opportunity_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atc.opportunity_management_system.entity.User;

@RepositoryRestResource(path = "users")
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
    
}
