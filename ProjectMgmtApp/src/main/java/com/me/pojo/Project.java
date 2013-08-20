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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.core.enums.AbstractLabeledEnum;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="Project")
public class Project implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "projectID", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int projectID;
	@Column(name = "projectName")
	private String projectName;
	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}




	@Column(name = "description")
	private String description;
	
	//One project has many teams involved
	@OneToMany(cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Team> teams;
	
	@Column(name="stage")
	private String stage;
	
	@ManyToOne
	@JoinColumn(name = "companyID")
	private Company company;
	
	public Project(){}
	
	public Project(Project project){
		this.description = project.description;
		this.projectID = project.projectID;
		this.projectName = project.projectName;
		this.teams = project.teams;
		this.stage = project.stage;
		this.company = project.company;
	}
	

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public int getProjectID() {
		return projectID;
	}

	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
