package com.atc.opportunity_management_system.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.atc.opportunity_management_system.entity.Transaction;

@RepositoryRestResource(path = "transactions")
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
