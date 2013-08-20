package com.me.pojo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
@Component
@Entity
@Table(name="WorkQueue")
@Inheritance(strategy=InheritanceType.JOINED)
public class WorkQueue implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public WorkQueue(){}
	
	public WorkQueue(WorkQueue workQueue){
		this.workQueueID =  workQueue.workQueueID;
		this.assignedTo = workQueue.assignedTo;
		this.description = workQueue.description;
	}
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="workQueueID", unique=true, nullable=false)
	private int workQueueID;
	
	@Column(name="description")
	private String description;
	
	
	@Column(name="assignedTo")
	private String assignedTo;
	
	@Column(name="title")
	private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	@Column(name="createdBy")
	private String createdBy;
	
	@ManyToOne
	@JoinColumn(name="projectID")
	private Project project;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public int getWorkQueueID() {
		return workQueueID;
	}

	public void setWorkQueueID(int workQueueID) {
		this.workQueueID = workQueueID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	

}
