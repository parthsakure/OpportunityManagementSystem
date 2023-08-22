package com.atc.opportunity_management_system.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Entity
@Table(name = "userDetails")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", nullable = false, unique = true, length = 30 )
    @NotNull(message = "can't keep this field empty")
    private String username;

    @Column(name = "firstName", nullable = false, length = 20)
    @NotNull(message = "can't keep this field empty")
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 30)
    @NotNull(message = "can't keep this field empty")
    private String lastName;

    @Email(message = "insert valid emailId")
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "contactNo", nullable = true, length = 15)
    @Pattern(regexp = "^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$", message = "invalid contact number")
    private String contactNo;

    @Column(name = "bbdBucks", nullable = false, length = 7)
    @PositiveOrZero(message = "BBD Bucks cant be negative")
    private int bbdBucks = 0;

    @Column(name = "active")
    @Value("true")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "company")
    private Company company;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dealOwner")
    private List<Opportunity> opportunities;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Transaction> transactions;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(this.role.getRole()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return "********";
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;

    }

    @Override
    public boolean isAccountNonLocked() {
        return true;

    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;

    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }

}
