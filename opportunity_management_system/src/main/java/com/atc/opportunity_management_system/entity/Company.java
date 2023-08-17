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
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
@Entity
@Table(name="company")
@Data
public class Company{
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="companyId",unique = true)
    private Long companyId;

    @Column(name="companyName",unique = true)
    @NotEmpty
    private String companyName;

    // @Column(name="industryId")
    // @NotEmpty
    // private int industryId;

    // @Column(name="locationId")
    // @NotEmpty
    // private int locationId;

    @Column(name="websiteUrl")
    @NotEmpty
    @Pattern(regexp = "^((https?|ftp|smtp):\\/\\/)?(www.)?[a-z0-9]+\\.[a-z]+(\\/[a-zA-Z0-9#]+\\/?)*$")
    private String websiteUrl;

    
    @Column(name="years",length=3)
    //to check if the number is positive
    @PositiveOrZero(message = "should be a positive number")
     //to check if the number is not fraction
    @Digits(fraction = 0, integer = 3, message ="years are not allowed in fraction")
    @NotEmpty
    private int years;
    
    @Column(name="active")
    @NotEmpty
    private boolean active=true;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, mappedBy = "company")
    @NotEmpty
    private List<User> users = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @NotEmpty
    @JoinColumn(name="industry")
    private Industry industry;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @NotEmpty
    @JoinColumn(name="location")
    private Location location;


}