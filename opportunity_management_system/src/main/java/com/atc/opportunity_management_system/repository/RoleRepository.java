package com.atc.opportunity_management_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atc.opportunity_management_system.entity.Role;


public interface RoleRepository extends JpaRepository<Role,Integer> {
    public Optional<Role> findByRole(String role);
}
