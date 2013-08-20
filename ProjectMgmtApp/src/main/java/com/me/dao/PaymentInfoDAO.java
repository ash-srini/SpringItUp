package com.me.dao;

import org.springframework.stereotype.Repository;

import com.me.pojo.PaymentInfo;
@Repository
public class PaymentInfoDAO extends DAO{
	
	public void create(PaymentInfo paymentInfo){
		try {
			begin();
			getSession().save(paymentInfo);
			commit();
			close();
		} catch (Exception e) {
			rollback();
			System.out.println(e);
		}

		
	}

}
