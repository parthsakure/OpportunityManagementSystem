package com.atc.opportunity_management_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="firstName", nullable = false)
    private String firstName;

    @Column(name="lastName", nullable = false)
    private String lastName;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="company", nullable = false)
    private String company;

    @Column(name="contactNo", nullable = false)
    private String contactNo;

    @Column(name="role", nullable = false)
    private String role;

    @Column(name="bbdBucks", nullable = false)
    private int bbdBucks;

    @Column(name="active", nullable = false)
    private Boolean active;
}
