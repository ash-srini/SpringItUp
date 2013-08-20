package com.me.pojo;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Staff")


public class Staff implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "staffID", unique=true, nullable=false)
	private int staffID;
	
	//many staff belong to one team
	@ManyToOne
	@JoinColumn(name="teamID")
	private Team team;

	//one to one mapping with user
	@OneToOne(cascade=CascadeType.ALL)
	private User user;
	
	//Constructors
	public Staff(){}
	
	
	public Staff(Staff staff){
		//this.staffID = staff.staffID;
		this.team = staff.team;
		this.staffID = staff.staffID;
		this.user = staff.user;
	}
	
	//Getter-Setters
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public int getStaffID() {
		return staffID;
	}


	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}


	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
