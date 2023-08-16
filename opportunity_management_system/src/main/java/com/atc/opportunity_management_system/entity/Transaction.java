package com.atc.opportunity_management_system.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Timestamp transactiontime;
    
	
    @Column(name="userId_fk", nullable = false)
	private Long userId_fk;
    
    @Column(name="opportunityId_fk", nullable = false)
	private Long opportunityId_fk;
 
    @Column(name="dealStageId_fk", nullable = false)
	private Long dealStageId_fk;
}
