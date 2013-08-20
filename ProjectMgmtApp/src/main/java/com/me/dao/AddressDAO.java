package com.me.dao;

import org.springframework.stereotype.Repository;

import com.me.pojo.Address;
@Repository
public class AddressDAO extends DAO{

	
	public Address create(Address address) throws Exception{
		
		

		try {
			Address add = new Address(address);
	        //getSession().beginTransaction();
			begin();
			
			getSession().save(address);
			commit();
			//close();
			return address;
			
		} catch (Exception e) {
			rollback();
			System.out.println(e);
			return address;
		}
		
		 

	}

	public AddressDAO() {
		super();
		
	}
}
