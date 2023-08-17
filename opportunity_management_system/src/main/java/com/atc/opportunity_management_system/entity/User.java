package com.atc.opportunity_management_system.entity;

import java.util.List;


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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    
    @Column(name="username", nullable = false, unique = true,length = 30)
    @NotBlank(message = "username is mandatory")
    private String username;

    @Column(name="firstName", nullable = false,length=20)
    private String firstName;
    
    @Column(name="lastName", nullable = false, length =30)
    private String lastName;

    @Email(message = "email is mandatory")
    @Column(name="email", nullable = false)
    private String email;

    @Column(name="contactNo", nullable = false ,length = 15)
    @Pattern(regexp="^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$")
    private String contactNo;

    @Column(name="bbdBucks", nullable = false, length = 7)
    private int bbdBucks=0;
    
    @Column(name="active")
    private Boolean active=true;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="roleId")
    private Role role;
    
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="company")
    private Company company;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, mappedBy = "dealOwner")
    private List<Opportunity> opportunities;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, mappedBy = "user")
    private List<Transaction> transactions;
}
