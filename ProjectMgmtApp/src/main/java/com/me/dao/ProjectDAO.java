package com.me.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.me.pojo.Company;
import com.me.pojo.Project;
import com.me.pojo.Team;
import com.me.pojo.User;
@Repository
public class ProjectDAO extends DAO{

	
	//Method to save project in the Project Table
		public void create(Project project) {

			try {
				
				begin();
				getSession().save(project);
				commit();
				close();
			} catch (Exception e) {
				rollback();
				System.out.println(e);
			}

		}

		//Method to get Project Object from Project table from Project Name
		public Project searchProject(String projectName){
			
		try{
			begin();
			String hql = "FROM Project WHERE projectName=:projectName";
			Query query = getSession().createQuery(hql);
			query.setParameter("projectName", projectName);
			Project p = (Project) query.uniqueResult();
			
			commit();
			close();
			return p;
			}
			catch(Exception e){
				System.out.println(e);
				rollback();
			}
			return null;
		}
		
		
		
	public List<Project> search(Company company){
		try{
			begin();
			String hql = "FROM Project WHERE company=:company";
			Query query = getSession().createQuery(hql);
			query.setEntity("company", company);
			List<Project> projects= query.list();
			commit();
			close();
			return projects;
		}
		catch(Exception e){
			System.out.println(e);
			rollback();
		}
		return null;
	}
		
	//update project
	public void updateProject(List<Team> teams, Project project){
		
	}
	
	//Search for project based on project id
	public Project searchByID(int pid){
		try{
			begin();
			String hql = "FROM Project WHERE projectID=:pid";
			Query query = getSession().createQuery(hql);
			query.setParameter("pid", pid);
			Project p = (Project) query.uniqueResult();
			commit();
			close();
			return p;
		}catch(Exception e){
			System.out.println(e);
			rollback();
		}
		return null;
	}
	
	//Delete project for a given id
	public void deleteProject(int pid){
		try{
			begin();
			String hql = "DELETE FROM Project WHERE projectID=:pid";
			Query query = getSession().createQuery(hql);
			query.setParameter("pid", pid);
			query.executeUpdate();
			commit();
			close();
			
		}catch(Exception e){
			System.out.println(e);
			rollback();
		}
	}
	
	//Update project description and stage
	public void updateProj(int pid, String stage, String description){
		try{
			begin();
			String hql = "UPDATE Project set stage=:stage, description=:description WHERE projectID=:pid";
			Query query = getSession().createQuery(hql);
			query.setParameter("stage", stage);
			query.setParameter("description", description);
			query.setParameter("pid", pid);
			query.executeUpdate();
			commit();
			close();
		}
		catch(Exception e){
			System.out.println(e);
			rollback();
		}
	}
	

}
