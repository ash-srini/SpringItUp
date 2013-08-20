package com.me.dao;

import java.util.List;







import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.me.pojo.Issue;
import com.me.pojo.Project;
import com.me.pojo.Task;
@Repository
public class TaskDAO extends DAO{

	
	public void create(Task task){
		try{
			begin();
			getSession().save(task);
			commit();
			close();
		}catch(Exception e){
			System.out.println(e);
			rollback();
		}
	}
	
	//get tasks by passing project
	
	public List<Task> searchByProject(Project project){
		try{
			begin();
			String hql = "FROM Task WHERE project=:project";
			Query query = getSession().createQuery(hql);
			query.setEntity("project", project);
			List<Task> tasks =  query.list();
			commit();
			close();
			return tasks;
		}
		catch(Exception e){
			System.out.println(e);
			rollback();
		}
		return null;
	}
	
	//delete task
	public void deleteTask(int workQueueID){
		try{
			begin();
			String hql = "DELETE FROM Task WHERE workQueueID=:workQueueID";
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
	
	//update task based on the work queue id
	public void editTask(String completetionLevel, int workQueueID){
		try{
			begin();
			String hql = "UPDATE Task set completetionLevel=:completetionLevel WHERE workQueueID=:workQueueID";
			Query query = getSession().createQuery(hql);
			query.setParameter("completetionLevel", completetionLevel);
			query.setParameter("workQueueID", workQueueID);
			query.executeUpdate();
			commit();
			close();
		}catch(Exception e){
			System.out.println(e);
			rollback();
		}
		
	}
	
	//search for Task based on workqueueID
	public Task searchByID(int wqid){
		try{
			begin();
			String hql = "FROM Task WHERE workQueueID=:wqid";
			Query query = getSession().createQuery(hql);
			query.setParameter("wqid", wqid);
			Task t = (Task)query.uniqueResult();
			commit();
			close();
			return t;
		}catch(Exception e){
			System.out.println(e);
			rollback();
		}
		return null;
	}
	
}
