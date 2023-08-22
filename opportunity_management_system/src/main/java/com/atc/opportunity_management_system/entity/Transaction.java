package com.atc.opportunity_management_system.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="transaction")
@Data
public class Transaction{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;
    
    @CreationTimestamp
    private Timestamp transactionTime;
    
	@ManyToOne
    @JoinColumn(name="userDetails", nullable = false)
	private User user;

    @ManyToOne
    @JoinColumn(name="opportunity", nullable = false)
	private Opportunity opportunity;
 
    @ManyToOne
    @JoinColumn(name="dealStage", nullable = false)
	private DealStage dealStage;
}
