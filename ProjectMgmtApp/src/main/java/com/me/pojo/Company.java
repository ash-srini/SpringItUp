package com.me.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;





import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name="Company")
public class Company implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "companyID",unique=true, nullable=false )
	private int companyID;
	@Column(name = "companyName")
	private String companyName;
	@Column(name = "numOfEmployees")
	private String numOfEmployees;
	
	//One company can have many users
	@OneToMany(mappedBy="company", cascade=CascadeType.ALL)
	private Set<User> employees;

	//One company will have only one address and vice versa
	@OneToOne(cascade=CascadeType.ALL)
	private Address address;
	
	//Payment info has one-to-one mapping with company i.e one company has one payment info

	@OneToOne(cascade=CascadeType.ALL)
	private PaymentInfo paymentInfo;
	
	@OneToMany(mappedBy="company", cascade=CascadeType.ALL)
	private List<Team> teamList;
	
	public PaymentInfo getPaymentInfo() {
		return paymentInfo;
	}

	public List<Team> getTeamList() {
		return teamList;
	}

	public void setTeamList(List<Team> teamList) {
		this.teamList = teamList;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	//default constructor
	public Company(){
	}
	
	public Company(String compnyName){
		employees = new HashSet<User>();
	}
	
	//constructor to create new company
	public Company(Company company){
		this.address = company.getAddress();
		this.companyID = company.getCompanyID();
		this.companyName = company.getCompanyName();
		this.employees = company.getEmployees();
		this.numOfEmployees = company.getNumOfEmployees();
		this.paymentInfo = company.getPaymentInfo();
	}
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}


	public int getCompanyID() {
		return companyID;
	}

	public void setCompanyID(int companyID) {
		this.companyID = companyID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getNumOfEmployees() {
		return numOfEmployees;
	}

	public void setNumOfEmployees(String numOfEmployees) {
		this.numOfEmployees = numOfEmployees;
	}

	public Set<User> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<User> employees) {
		this.employees = employees;
	}

	public void newEmployeeList(User user){
		
		employees.add(user);
	}
	

}
