package com.me.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.me.pojo.Company;
import com.me.pojo.Project;
import com.me.pojo.Team;
import com.me.pojo.User;
@Repository
public class TeamDAO extends DAO{
	
	//Method to save team in the Team Table
		public void create(Team team) {

			try {
				
				begin();
				getSession().save(team);
				commit();
				close();
			} catch (Exception e) {
				rollback();
				System.out.println(e);
			}

		}

//		//Method to get Team Object from Team table from Team Name
//		public Team searchByName(String teamName, Company company){
//			begin();
//			String hql = "FROM Team WHERE teamName=:teamName";
//			Query query = getSession().createQuery(hql);
//			query.setParameter("teamName", teamName);
//			//get team of the company in method declaration
//			List<Team>  rs = query.list();
//			for(int i=0; i<rs.size(); i++){
//				if(rs.get(i).getCompany().equals(company)){
//					Team resultTeam = (Team) rs.get(i);
//					commit();
//					return resultTeam;
//				}
//			}
//			//Team resultTeam = (Team) query.uniqueResult();
//			
//			commit();
//			return null;
//		}

		//searched db for teams belonging to onw company
		@SuppressWarnings("unchecked")
		public List<Team> searchByCompany(Company company) throws Exception{
			try{
				begin();
				String hql = "FROM Team WHERE company=:company";
				Query query = getSession().createQuery(hql);
				query.setEntity("company", company);
				List<Team> rs = query.list();
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
		
		//update team info
		public void updateTeam(Team team, Project project){
			try{
				begin();
				String teamName = team.getTeamName();
				String hql = "UPDATE Team set project=:project WHERE teamName=:teamName ";
				Query query = getSession().createQuery(hql);
				query.setEntity("project", project);
				query.setParameter("teamName", teamName);
				query.executeUpdate();
				commit();
				close();
			}
		
			catch(Exception e){
				System.out.println(e);
				rollback();
			}
		}
		
		//get List of team based on project
		
		public List<Team> projTeams(Project project){
			try{
				begin();
				String hql = "FROM Team WHERE project=:project";
				Query query = getSession().createQuery(hql);
				query.setParameter("project", project);
				List<Team> teams = query.list();
				commit();
				close();
				return teams;
			}catch(Exception e){
				System.out.println(e);
				rollback();
		
			return null;	}
		}
		
		
}