package com.me.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.me.pojo.Issue;
import com.me.pojo.Project;

@Repository
public class IssueDAO extends DAO{
	
	public void create(Issue issue){
		try{
			begin();
			getSession().save(issue);
			commit();
			close();
		}catch(Exception e){
			System.out.println(e);
			rollback();
			
		}
	}
	
	
	public List<Issue> searchByProject(Project project){
		try{
			begin();
			String hql = "FROM Issue WHERE project=:project";
			Query query = getSession().createQuery(hql);
			query.setEntity("project", project);
			List<Issue> issues = query.list();
			
			commit();
			close();
			return issues;
		}catch(Exception e){
			System.out.println(e);
			rollback();
		}
		return null;
	}
	
	//method to delete Issue from db
	public void deleteIssue(int workQueueID){
		try{
			begin();
			String hql = "DELETE FROM Issue WHERE workQueueID=:workQueueID";
			Query query = getSession().createQuery(hql);
			query.setParameter("workQueueID", workQueueID);
			int i =query.executeUpdate();
			System.out.println(i+"rows affected");
			commit();
			close();
		}
		catch(Exception e){
			System.out.println(e);
			rollback();
		}
	}
	
	//search for issue based on workqueueID
	public Issue searchByID(int wqid){
		try{
			begin();
			String hql = "FROM Issue WHERE workQueueID=:wqid";
			Query query = getSession().createQuery(hql);
			query.setParameter("wqid", wqid);
			Issue i = (Issue)query.uniqueResult();
			commit();
			close();
			return i;
		}catch(Exception e){
			System.out.println(e);
			rollback();
		}
		return null;
	}
	
	//update priority description and status of issue
	public void editIssue(String description,String status, String priority, int workQueueID){
		try{
			begin();
			String hql = "UPDATE Issue set description=:description,status=:status,priority=:priority WHERE workQueueID=:workQueueID";
			Query query = getSession().createQuery(hql);
			query.setParameter("description", description);
			query.setParameter("status", status);
			query.setParameter("priority", priority);
			query.setParameter("workQueueID", workQueueID);
			query.executeUpdate();
			commit();
			close();
		}catch(Exception e){
			System.out.println(e);
			rollback();
		}
		
	}
	
}
