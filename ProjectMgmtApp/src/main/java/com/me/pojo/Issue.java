package com.me.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="Issue")
@PrimaryKeyJoinColumn(name="workQueueID")
public class Issue extends WorkQueue implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Column(name="priority")
	private String priority;
	
	@Column(name="status")
	private String status;
	

	@ManyToOne
	@JoinColumn(name="projectID")
	private Project project;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
