package com.me.pojo;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="Team")
public class Team implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "teamID",unique=true, nullable=false )
	private int teamID;
	
	@Column(name="teamName")
	private String teamName;
	
	//One team has many staff
	@OneToMany( cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private List<Staff> staffList;
	
	@ManyToOne
	@JoinColumn(name = "companyID")
	private Company company;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="projectID")
	private Project project;
	


	public Team(){}
	
	public Team(Team team){
		this.teamID = team.teamID;
		this.staffList = team.staffList;
		this.teamName = team.teamName;
		this.company = team.company;
		this.project = team.project;
	}
	
	

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public int getTeamID() {
		return teamID;
	}
	public void setTeamID(int teamID) {
		this.teamID = teamID;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public List<Staff> getStaffList() {
		return staffList;
	}
	public void setStaffList(List<Staff> staffList) {
		this.staffList = staffList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	

	

}
