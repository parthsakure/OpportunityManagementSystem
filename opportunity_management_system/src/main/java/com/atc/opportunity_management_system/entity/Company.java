package com.atc.opportunity_management_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Id;
@Entity
@Table(name="company")
@Data
public class Company{
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="companyId")
    private Long companyId;

    @Column(name="companyName")
    private String companyName;

    @Column(name="industryId_fk")
    private int industryId_fk;

    @Column(name="location_fk")
    private int location_fk;

    @Column(name="websiteUrl")
    private String websiteUrl;

    
    @Column(name="years")
    private int years;
    
    @Column(name="active")
    private boolean active;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private List<Users> users = new ArrayList<>();

    @ManyToOne
    private Industry industry;

    @ManyToOne
    private Location location;
}