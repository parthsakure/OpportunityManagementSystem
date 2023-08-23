package com.atc.opportunity_management_system.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

// import jakarta.validation.constraints.PositiveOrZero;
@Entity
@Table(name = "company")
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long companyId;

    @Column(name = "companyName")
    private String companyName;

    @Column(name = "websiteUrl")
    @Pattern(regexp = "^((https?|ftp|smtp):\\/\\/)?(www.)?[a-z0-9]+\\.[a-z]+(\\/[a-zA-Z0-9#]+\\/?)*$", message = "Invalid URL format")
    @NotNull(message = "can't keep this field empty") 
    private String websiteUrl;

    @Column(name = "years")
    @Positive(message = "The company needs to be at least a year old")
    @Digits(fraction = 0, integer = 3, message = "Years can't be in fraction")
    @NotNull(message = "Years cannot be null")
    private Integer years;

    @Column(name = "active")
    @NotNull(message = "Active cannot be null")
    private Boolean active;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    @ManyToOne
    @NotNull(message = "Industry cannot be null")
    @JoinColumn(name = "industry")
    private Industry industry;

    @ManyToOne
    @NotNull(message = "Location cannot be null")
    @JoinColumn(name = "location")
    private Location location;

}
