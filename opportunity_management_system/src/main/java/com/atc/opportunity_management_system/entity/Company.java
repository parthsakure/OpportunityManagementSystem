package com.atc.opportunity_management_system.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

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
// import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

// import jakarta.validation.constraints.PositiveOrZero;
@Entity
@Table(name = "company")
@Data
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "companyId", unique = true)
    private Long companyId;

    @Column(name = "companyName")
    @NotEmpty(message = "CompanyName cannot be empty")
    private String companyName;

    // @Column(name="industryId")
    // @NotEmpty
    // private int industryId;

    // @Column(name="locationId")
    // @NotEmpty
    // private int locationId;

    @Column(name = "websiteUrl")
    @NotEmpty
    @Pattern(regexp = "^((https?|ftp|smtp):\\/\\/)?(www.)?[a-z0-9]+\\.[a-z]+(\\/[a-zA-Z0-9#]+\\/?)*$")
    private String websiteUrl;

    @Column(name = "years")
    @NotEmpty
    @Positive(message = "the company needs to be atleast a year old")
    @Digits(fraction = 0, integer = 3, message = "years can't be in fraction")
    @NotEmpty(message = "Years cannot be empty")
    private int years;

    @Column(name = "active")
    @NotEmpty
    @Value("true")
    @NotEmpty(message = "Active cannot be empty")
    private boolean active;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    @NotEmpty(message = "User cannot be empty")
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    @ManyToOne
    @NotEmpty(message = "industry cannot be empty")
    @JoinColumn(name = "industry")
    private Industry industry;

    @ManyToOne
    @NotEmpty(message = "Location cannot be empty")
    @JoinColumn(name = "location")
    private Location location;

}