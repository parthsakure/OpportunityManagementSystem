package com.atc.opportunity_management_system.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table
public class industry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int industryId;

    private String companyName;

}
