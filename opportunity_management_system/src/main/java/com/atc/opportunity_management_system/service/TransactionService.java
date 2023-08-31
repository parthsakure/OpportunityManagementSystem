package com.atc.opportunity_management_system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.atc.opportunity_management_system.entity.ErrorMessage;
import com.atc.opportunity_management_system.entity.Transaction;
import com.atc.opportunity_management_system.entity.User;
import com.atc.opportunity_management_system.repository.TransactionRepository;
import com.atc.opportunity_management_system.repository.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Service
public class TransactionService {
    
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    EntityManager entityManager;

    @Autowired
    UserRepository userRepository;

    //method to get all transactions for admin
    public ResponseEntity<Object> getAllTransactions()
    {
        //get if user is admin or not
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = loggedInUser.getAuthorities().stream().anyMatch(auth->auth.getAuthority().equals("ROLE_ADMIN"));

        if(isAdmin)
        {
            //get all transactions
            List<Transaction> transactions = transactionRepository.findAll();

            //if no transactions are present
            if(transactions.isEmpty())
            {
                return new ResponseEntity<Object>(new ErrorMessage("No Transactions Present",HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND); 
            }
            
            //return all transactions
            return ResponseEntity.ok(transactions);
        }

        //if user is not admin
        return new ResponseEntity<Object>(new ErrorMessage("You are not an admin",HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    //method to get all transactions for a single user
    public ResponseEntity<Object> getMyTransactions()
    {
        //get user information
        // System.out.println("HELLO");
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get();

        //get transactions of user
        // TypedQuery<Transaction> query = entityManager.createQuery("Select o from Transaction o where o.user=?1", Transaction.class);
        // query.setParameter(1, user);
        List<Transaction> transactions = transactionRepository.findByUser(user);
        // System.out.println(transactions);
        //if no transactions are present
        if(transactions.isEmpty())
        {
            return new ResponseEntity<Object>(new ErrorMessage("No Transactions Present",HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND); 
        }

        //return all of the users transactions
        return ResponseEntity.ok(transactions);
    }
}
