package com.atc.opportunity_management_system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="location")
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long locationId;
	
    @Column(name="postalCode", nullable = false)
	private int postalCode;
	
    @Column(name="countryId_fk", nullable = false)
	private Long countryId_fk;

	public Location() {
		super();
	}

	public Location(int postalCode, Long countryId_fk) {
		super();
		this.postalCode = postalCode;
		this.countryId_fk = countryId_fk;
	}

	public Location(Long locationId, int postalCode, Long countryId_fk) {
		super();
		this.locationId = locationId;
		this.postalCode = postalCode;
		this.countryId_fk = countryId_fk;
	}

	public Long getLocationId() {
		return locationId;
	}

	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public Long getCountryId_fk() {
		return countryId_fk;
	}

	public void setCountryId_fk(Long countryId_fk) {
		this.countryId_fk = countryId_fk;
	}

	@Override
	public String toString() {
		return "Location [locationId=" + locationId + ", postalCode=" + postalCode + ", countryId_fk=" + countryId_fk
				+ "]";
	}

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "location")
    private List<Company> companies = new ArrayList<>();
    
 
}
