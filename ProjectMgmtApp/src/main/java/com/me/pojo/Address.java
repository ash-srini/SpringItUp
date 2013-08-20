package com.me.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="Address")
public class Address implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "addressID", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int addressID;
	@Column(name = "streetName")
	private String streetName;
	@Column(name = "city")
	private String city;
	@Column(name = "state")
	private String state;
	@Column(name = "country")
	private String country;
	@Column(name="zipCode")
	private String zipCode;


	public Address(){
		
	}
	
	public Address(Address address){
		this.addressID = address.addressID;
		this.city = address.city;
		this.country = address.country;
		this.streetName = address.streetName;
		this.state = address.state;
	}
	
	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}


	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	
}
