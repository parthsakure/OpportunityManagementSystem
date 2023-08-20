package com.atc.opportunity_management_system.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atc.opportunity_management_system.entity.Role;


@RepositoryRestResource(path="roles")
public interface RoleRepository extends JpaRepository<Role,Integer> {
    public Optional<Role> findByRole(String role);
}
