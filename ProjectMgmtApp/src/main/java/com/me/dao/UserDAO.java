package com.me.dao;


import java.util.List;


import org.hibernate.Query;
import org.springframework.stereotype.Repository;


import com.me.pojo.Company;
import com.me.pojo.User;
@Repository
public class UserDAO extends DAO {

	//Method to save user in the User Table
	public void create(User user) {

		try {
			//System.out.println("user name is as follows"+ user.getFirstName()+"\t"+user.getLastName());
			begin();
			getSession().save(user);
			commit();
			close();
		} catch (Exception e) {
			rollback();
			System.out.println(e);
		}

	}

	//Method to get User Object from User table from Email and Password
	public User searchUser(String email, String password, User tempUser) throws Exception{
		 boolean flag =false;
		 User resultUser = null;
		
		try{
		begin();
		//checking email
		
		String hql = "FROM User WHERE email=:email";
		Query query = getSession().createQuery(hql);
		query.setParameter("email", email);
		 resultUser = (User) query.uniqueResult();
		if(!resultUser.equals(null)){
			flag = true;
		}
		else{
			flag=false;
		}
		commit();
		close();
		System.out.println("***"+resultUser.getEmail()+resultUser.getFirstName()+"***");
		return resultUser;
		}
		catch(Exception e){
			rollback();
			System.out.println(e);
		}
		if(flag==true){
		return resultUser;
		}
		else{
			return tempUser;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<User> searchByCompany(Company company) throws Exception{
		
		//String company= compnay.getCompanyName();
		
		try{
			begin();
			String hql = "FROM User WHERE company=:company";
			Query query = getSession().createQuery(hql);
			query.setEntity("company", company);
			List<User> rs = query.list();
			commit();
			close();
				return rs;
			
		}
		catch(Exception e){
			rollback();
			System.out.println(e);
	}
		return null;
	
}
	public User searchByEmail(String email, User tempUser) throws Exception{
		 boolean flag =false;
		 User resultUser = null;
		
		try{
		begin();
		//checking email
		
		String hql = "FROM User WHERE email=:email";
		Query query = getSession().createQuery(hql);
		query.setParameter("email", email);
		 resultUser = (User) query.uniqueResult();
		if(!resultUser.equals(null)){
			flag = true;
		}
		else{
			flag=false;
		}
		commit();
		close();
		
		return resultUser;
		}
		catch(Exception e){
			rollback();
			System.out.println(e);
		}
		
		if(flag==true){
		return resultUser;
		}
		else{
			return tempUser;
		}
	}
	
	
	public User search(String email) throws Exception{
		try{
			begin();
			//checking email
			
			String hql = "FROM User WHERE email=:email";
			Query query = getSession().createQuery(hql);
			query.setParameter("email", email);
			 User resultUser = (User) query.uniqueResult();
			 commit();
			 close();
			 return resultUser;
		}
		catch(Exception e){
			System.out.println(e);
			rollback();
		}
		return null;
	}
	
	public void resetPassword(int uid, String newpassword){
		
		try{
			begin();
			String hql = "UPDATE User set password=:newpassword WHERE userID=:uid";
			Query query = getSession().createQuery(hql);
			query.setParameter("uid", uid);
			query.setParameter("newpassword", newpassword);
			query.executeUpdate();
			commit();
			close();
		}catch(Exception e){
			System.out.println(e);
			rollback();
		}
	}
	
}
