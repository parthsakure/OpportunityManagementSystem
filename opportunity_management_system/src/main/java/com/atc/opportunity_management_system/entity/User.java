package com.atc.opportunity_management_system.entity;

import jakarta.persistence.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="username", nullable = false, unique = true)
    private String username;

    @Column(name="firstName", nullable = false)
    private String firstName;
    

    @Column(name="lastName", nullable = false)
    private String lastName;

    @Column(name="email", nullable = false)
    private String email;


    @Column(name="contactNo", nullable = false)
    private String contactNo;

    @ManyToOne
    @JoinColumn(name="roleId")
    private Role role;

    @Column(name="bbdBucks", nullable = false)
    private int bbdBucks;

    @Column(name="active", nullable = false)
    private Boolean active;

    @ManyToOne
    private Company company;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, mappedBy = "user")
    private Opportunity opportunity;

}
