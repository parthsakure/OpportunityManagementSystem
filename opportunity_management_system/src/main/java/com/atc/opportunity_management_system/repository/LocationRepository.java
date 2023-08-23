package com.atc.opportunity_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atc.opportunity_management_system.entity.Location;

@RepositoryRestResource(path = "location")
public interface LocationRepository extends JpaRepository<Location, Long>{

}
