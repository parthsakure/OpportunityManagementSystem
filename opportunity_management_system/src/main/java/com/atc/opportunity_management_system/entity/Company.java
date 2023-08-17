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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
@Entity
@Table(name="company")
@Data
public class Company{
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="companyId")
    private Long companyId;

    @Column(name="companyName")
    @NotEmpty(message = "CompanyName cannot be empty")
    private String companyName;

    @Column(name="industryId")
    @NotEmpty
    private int industryId;

    @Column(name="locationId")
    @NotEmpty
    private int locationId;

    @Column(name="websiteUrl")
    @NotEmpty
    @Pattern(regexp = "(https:\\/\\/www\\.|http:\\/\\/www\\.|https:\\/\\/|http:\\/\\/)?[a-zA-Z0-9]{2,}(\\.[a-zA-Z0-9]{2,})(\\.[a-zA-Z0-9]{2,})?")
    private String websiteUrl;

    
    @Column(name="years")
    @NotEmpty(message = "Years cannot be empty")
    private int years;
    
    @Column(name="active")
    @NotEmpty(message = "Active cannot be empty")
    private boolean active;

    @OneToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH}, mappedBy = "company")
    @NotEmpty(message = "User cannot be empty")
    private List<User> users = new ArrayList<>();

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @NotEmpty(message = "industry cannot be empty")
    @JoinColumn(name="industry")
    private Industry industry;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @NotEmpty(message = "Location cannot be empty")
    @JoinColumn(name="location")
    private Location location;


}