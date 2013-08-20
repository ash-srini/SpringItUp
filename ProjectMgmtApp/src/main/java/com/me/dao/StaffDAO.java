package com.me.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.me.pojo.Staff;
import com.me.pojo.Team;
import com.me.pojo.User;
@Repository
public class StaffDAO extends DAO{
	
	//Method to save staff in the satff Table
		public void create(Staff staff) {

			try {
				
				begin();
				getSession().save(staff);
				commit();
				close();
			} catch (Exception e) {
				rollback();
				System.out.println(e);
			}

		}

	//
	public boolean searchStaff(User user){
		
		boolean flag = false;
		try{
			begin();
			String hql = "FROM Staff WHERE user=:user";
			Query query = getSession().createQuery(hql);
			query.setEntity("user", user);
			Staff rsstaff = (Staff) query.uniqueResult();
			commit();
			close();
			User u = rsstaff.getUser();
			if(!rsstaff.equals(null)){
				flag=true;
			}
			else {
				flag=false;
			}
			if (flag == true){
				return true;
			}else{
			return false;
			}
		}
		catch(Exception e){
			System.out.println(e);
			rollback();
		}
		return false;
	
	}
	
	public List<Staff> searchByTeam(Team team){
		try{
			begin();
			String hql = "FROM Staff WHERE team=:team";
			Query query = getSession().createQuery(hql);
			query.setEntity("team", team);
			List<Staff> staffList = query.list();
			commit();
			close();
			return staffList;
			
			
		}
		catch(Exception e){
			System.out.println(e);
			rollback();
		}
		return null;
		
	}
	
	public Staff searchByUser(User user){
		try{
			begin();
			String hql = "FROM Staff WHERE user=:user";
			Query query = getSession().createQuery(hql);
			query.setEntity("user", user);
			Staff rsstaff = (Staff) query.uniqueResult();
			commit();
			close();
			return rsstaff;
		}
		catch(Exception e){
			System.out.println(e);
			rollback();
		}
		return null;
	}
	
}
