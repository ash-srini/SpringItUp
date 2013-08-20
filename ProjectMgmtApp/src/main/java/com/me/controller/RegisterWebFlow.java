package com.me.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.me.dao.AddressDAO;
import com.me.dao.CompanyDAO;
import com.me.dao.PaymentInfoDAO;
import com.me.dao.UserDAO;
import com.me.pojo.Address;
import com.me.pojo.Company;
import com.me.pojo.PaymentInfo;
import com.me.pojo.User;

@Controller
public class RegisterWebFlow {
		@Autowired
		AddressDAO addressDAO;
		@Autowired
		CompanyDAO companyDAO;
		@Autowired
		UserDAO userDAO;
		@Autowired
		PaymentInfoDAO paymentInfoDAO;
	
		//checks if user email already exists
		public User step1(User user) throws Exception{
			user.setRole("CompanyAdmin");
			User u = userDAO.search(user.getEmail());
			
				return u;
			
		}
	//deals with the submitted form in step1.jsp
		public Company step2( Address address, String companyName, String numOfEmployees, User user) throws Exception{
			//set role value in user
			//here role is always CompanyAdmin as the 'company admin' is creating a company profile
			user.setRole("CompanyAdmin");
			
			//create new company
			Company company = new Company();
			
			//set address from address pojo
			company.setAddress(address);
			company.setCompanyName(companyName);
			company.setNumOfEmployees(numOfEmployees);
			System.out.println(address.getCity()+"*******");
			//set company in user
			user.setCompany(company);
			return company;
		}

	
	//deals with the submitted form in step3.jsp
	public boolean step3(Address address, Company company, User user, PaymentInfo paymentInfo) throws Exception{
		company.setPaymentInfo(paymentInfo);
		//****NEED  TO WRITE MORE LOGIC FOR VALIDATING THE REGISTERATION PROCEDURE****
		//return true only if values are populated in the form
		if(!address.equals(null) && !company.equals(null) && !user.equals(null) && !paymentInfo.equals(null)){
			
		//save address, user, company, paymentinfo in their table using dao
		addressDAO.create(address);
		
		paymentInfoDAO.create(paymentInfo);
		companyDAO.create(company);
		userDAO.create(user);
		
		return true;
	}
		else{
			return false;
		}
	}
	
	
}
