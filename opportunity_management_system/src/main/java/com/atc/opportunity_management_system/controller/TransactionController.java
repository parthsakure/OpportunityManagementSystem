package com.atc.opportunity_management_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atc.opportunity_management_system.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    
    @Autowired
    TransactionService transactionService;

    @GetMapping("/")
    private ResponseEntity<Object> getAllTransactions()
    {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/mytransactions")
    private ResponseEntity<Object> getMyTransactions()
    {
        return transactionService.getMyTransactions();
    }
}
