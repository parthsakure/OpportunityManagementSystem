package com.atc.opportunity_management_system.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, mappedBy = "company")
    private List<User> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="industryId")
    private Industry industry;

    @ManyToOne
    @JoinColumn(name="locationId")
    private Location location;


}