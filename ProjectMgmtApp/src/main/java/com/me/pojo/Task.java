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
@Table(name="Task")
@PrimaryKeyJoinColumn(name="workQueueID")
public class Task extends WorkQueue implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="completitionLevel")
	private String completetionLevel;


	
	public String getCompletetionLevel() {
		return completetionLevel;
	}



	public void setCompletetionLevel(String completetionLevel) {
		this.completetionLevel = completetionLevel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
