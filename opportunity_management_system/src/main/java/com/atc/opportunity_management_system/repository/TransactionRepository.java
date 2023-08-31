package com.atc.opportunity_management_system.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atc.opportunity_management_system.entity.Transaction;
import com.atc.opportunity_management_system.entity.User;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

    List<Transaction> findByUser(User user);
}
