package com.me.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.me.pojo.Company;
import com.me.pojo.User;
@Repository
public class CompanyDAO extends DAO{

	
	public void create(Company company) {

		try {
			begin();
			getSession().save(company);
			commit();
			close();
		} catch (Exception e) {
			rollback();
			System.out.println(e);
		}

	}
	

	
}
