package com.me.controller;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.dao.DAO;
import com.me.dao.IssueDAO;
import com.me.dao.ProjectDAO;
import com.me.dao.StaffDAO;
import com.me.dao.TaskDAO;
import com.me.dao.TeamDAO;
import com.me.dao.UserDAO;
import com.me.pojo.Issue;
import com.me.pojo.Project;
import com.me.pojo.Staff;
import com.me.pojo.Task;
import com.me.pojo.Team;
import com.me.pojo.User;

@Controller
public class ManagerFunctionsController {

	@Autowired
	private Project project;
	
	@Autowired
	private ProjectDAO projectDAO;
	
	@Autowired
	private TeamDAO teamDAO;
	
	@Autowired
	private Task task;
	
	@Autowired
	private Issue issue;
	
	@Autowired
	private StaffDAO staffDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private TaskDAO taskDAO;
	
	@Autowired
	private IssueDAO issueDAO;
	
	@RequestMapping(value="/manager/manageProject.htm", method=RequestMethod.GET)
	public String managerProject(Model model, HttpServletRequest request) throws Exception{
		
		//model.addAttribute("project", project);
		//get teams from company
		
		//get project from teams
		HttpSession servletSession = request.getSession();
		User u = (User) servletSession.getAttribute("loggedInUser");
		List<Team> resultTeamList = teamDAO.searchByCompany(u.getCompany());
		List<Project> projects = projectDAO.search(u.getCompany());
	
		request.setAttribute("projects", projects);
		return"projectManagement";
	}
	
	@RequestMapping(value="/manager/addProject.htm", method=RequestMethod.GET)
	public String addProject(Model model, HttpServletRequest request) throws Exception{
		
		model.addAttribute("project",project);
		HttpSession servletSession = request.getSession();
		User u = (User) servletSession.getAttribute("loggedInUser");
		List<Team> resultTeamList = teamDAO.searchByCompany(u.getCompany());
		//DAO.close();
		request.setAttribute("resultTeamList", resultTeamList);
		
		return "addProject";
		
	}
	
	@RequestMapping(value="/manager/addProject.htm", method=RequestMethod.POST)
	public String projectAdded( HttpServletRequest request) throws Exception{
		
		//store project in db using dao
		String projStage = request.getParameter("stage");
		project.setProjectName(request.getParameter("projectName"));
		project.setDescription(request.getParameter("description"));

		
		
		return "redirect:/manager/addTeamsToProject.htm";
		
	}
	
	@RequestMapping(value="/manager/addTeamsToProject.htm", method=RequestMethod.GET)
	public String addTeamsToProject(HttpServletRequest request) throws Exception{
		
		
		HttpSession servletSession = request.getSession();
		User u = (User) servletSession.getAttribute("loggedInUser");
		List<Team> resultTeamList = teamDAO.searchByCompany(u.getCompany());
		//DAO.close();
		int count = resultTeamList.size();
		List<Team> tempTeamList = new ArrayList<Team>();
		for(int i=0; i<count; i++){
			
			if(resultTeamList.get(i).getProject() == null){
				tempTeamList.add(resultTeamList.get(i));
			}
		}
		request.setAttribute("tempTeamList", tempTeamList);
		request.setAttribute("resultTeamList", resultTeamList);
		
		
		return "addTeamToProject";
	}
	
	@RequestMapping(value="/manager/addTeamsToProject.htm", method=RequestMethod.POST)
	public String setProjectTeams(HttpServletRequest request) throws Exception{
		
		String stage1 = request.getParameter("stage1");
		String stage2 = request.getParameter("stage2");
		String stage3 = request.getParameter("stage3");
		
		List<Team> projTeams = new ArrayList<Team>();
		HttpSession servletSession = request.getSession();
		User u = (User) servletSession.getAttribute("loggedInUser");
		List<Team> tempTeamList = teamDAO.searchByCompany(u.getCompany());
		for(int i=0; i<tempTeamList.size(); i++){
			if(tempTeamList.get(i).getTeamName().equals(request.getParameter(tempTeamList.get(i).getTeamName()))){
		System.out.println(request.getParameter(tempTeamList.get(i).getTeamName()));
			projTeams.add(tempTeamList.get(i));
			}
		}
		servletSession.setAttribute("projTeams", projTeams);
		
		project.setTeams(projTeams);
		return "redirect:/manager/setProjectStage.htm";
		
	}
	
	@RequestMapping(value="/manager/setProjectStage.htm", method=RequestMethod.GET)
	public String setProjectStage(HttpServletRequest request){
		//System.out.println("size="+project.getTeams().size());
		request.setAttribute("projectTeams", project.getTeams());
		
		
		
		return "setProjectStage";
	}
	
	@RequestMapping(value="/manager/setProjectStage.htm", method=RequestMethod.POST)
	public String persistProject(HttpServletRequest request) throws Exception{
		
		HttpSession servletSession = request.getSession();
		User u = (User) servletSession.getAttribute("loggedInUser");
		//
		List<Team> resultTeamList = teamDAO.searchByCompany(u.getCompany());
		String stage = request.getParameter("stage");
		
		for(int i=0; i<resultTeamList.size();i++){
			if(resultTeamList.get(i).getTeamName().equals(stage)){
				Team t = resultTeamList.get(i);
				project.setCompany(u.getCompany());
				project.setStage(resultTeamList.get(i).getTeamName());
				projectDAO.create(project);
				
			}
			}
		
		
		//System.out.println("****"+project.getStage()+project.getTeams());
		
		
		List<Team> teams = (List<Team>) servletSession.getAttribute("projTeams");
		for(int i=0; i<teams.size(); i++){
		teamDAO.updateTeam(teams.get(i), project);
		}
		 return "addedProj";
	}
	

	/*
	 * This method adds a specific team to a specific project in the company
	 */
	@RequestMapping(value="/manager/addTeamsToSpecifiedProject.htm", method=RequestMethod.GET)
	public String addTeamToSpecifiedProject(HttpServletRequest request) throws Exception{
		HttpSession servletSession = request.getSession();
		User u = (User) servletSession.getAttribute("loggedInUser");
		List<Team> resultTeamList = teamDAO.searchByCompany(u.getCompany());
		
		//get all the projects
		List<Project> projects = projectDAO.search(u.getCompany());
		
		request.setAttribute("projects", projects);

		request.setAttribute("resultTeamList", resultTeamList);
		
		
		//get the teams that have not yet been assigned to a project
		int count = resultTeamList.size();
		List<Team> tempTeamList = new ArrayList<Team>();
		for(int i=0; i<count; i++){
			
			if(resultTeamList.get(i).getProject() == null){
				tempTeamList.add(resultTeamList.get(i));
			}
		}
		request.setAttribute("tempTeamList", tempTeamList);
		
		
		return "addTEamToSpecifiedProject";
	}
	
	/*
	 * CHECK LATER
	 */
	@RequestMapping(value="/manager/addTeamsToSpecifiedProject.htm", method=RequestMethod.POST)
		public String projToTeam(HttpServletRequest request) throws Exception{
		HttpSession servletSession = request.getSession();
		User u = (User) servletSession.getAttribute("loggedInUser");
		
		Project p = projectDAO.searchProject(request.getParameter("projectName"));
		List<Team> teamList = teamDAO.searchByCompany(u.getCompany());
		for(int i=0; i<teamList.size(); i++){
			if(teamList.get(i).getTeamName().equals(request.getParameter("team"))){
				p.getTeams().add(teamList.get(i));
			}
		}
		
		projectDAO.updateProject(p.getTeams() ,p);
		
		for(int i=0; i<teamList.size(); i++){
			if(teamList.get(i).getTeamName().equals(request.getParameter("team"))){
				teamDAO.updateTeam(teamList.get(i), p);
			}
		}
		
		return "";
	}
	
	/*
	 * The following methods are for managing Tasks
	 */
	
	@RequestMapping(value="/manager/manageTask.htm", method=RequestMethod.GET)
	public String manageTask(HttpServletRequest request, Model model){
		model.addAttribute("task", task);
		//code to get all the task and display in the table
		HttpSession servletSession = request.getSession();
		User u = (User) servletSession.getAttribute("loggedInUser");
		
		List<Task> tasks = new ArrayList<Task>();
		
		List<Project> projects =  projectDAO.search(u.getCompany());
		for(int i=0; i<projects.size(); i++){
			if(taskDAO.searchByProject(projects.get(i)) != null){
				List<Task> temp = taskDAO.searchByProject(projects.get(i));
				for(int j=0; j<temp.size(); j++){
					tasks.add(temp.get(j));
				}
			}
		}
		request.setAttribute("tasks", tasks);
		return "manageTask";
	}
	
	/*
	 * Select Project to which you want to add the task
	 */
	@RequestMapping(value="/manager/selectProjForTask.htm", method=RequestMethod.GET)
	public String selectProjectForTask(HttpServletRequest request){
		HttpSession servletSession = request.getSession();
		User u = (User) servletSession.getAttribute("loggedInUser");
		
		List<Project> projects = projectDAO.search(u.getCompany());
		
		request.setAttribute("projects", projects);
		return "selectProjForTask";
	}
	
	@RequestMapping(value="/manager/selectProjForTask.htm", method=RequestMethod.POST)
	public String setProjectToTask(HttpServletRequest request){
		//look from project based on project name and set it to task
		String projName = request.getParameter("projName");
		Project selectedProj = projectDAO.searchProject(projName);
		task.setProject(selectedProj);
		return "redirect:/manager/createTask.htm";
	}
	
	/*
	 * set other task properties and persist task
	 */
	@RequestMapping(value="/manager/createTask.htm", method=RequestMethod.GET)
	public String createTask(Model model,HttpServletRequest request) throws Exception{
		//display create task form
		model.addAttribute("task", task);
		HttpSession servletSession = request.getSession();
		User u = (User) servletSession.getAttribute("loggedInUser");
		System.out.println( task.getProject().getProjectName());
		//teams in the company
		List<Team> teamList = teamDAO.searchByCompany(u.getCompany());
	
		
		//all staff belonging to the teams in the project are added to staffList
		List<Staff> staffList = new ArrayList<Staff>();
		for(int i=0; i<teamList.size(); i++){
			//if the team has a project
			if(teamList.get(i).getProject() != null){
				//and the project in the team is same as the project name we selected in the previous step
				if(teamList.get(i).getProject().getProjectName().equals(task.getProject().getProjectName())){
					if(staffDAO.searchByTeam(teamList.get(i)) != null){
					List<Staff> temp =  staffDAO.searchByTeam(teamList.get(i));
					for(int j=0; j<temp.size(); j++){
						staffList.add(temp.get(j));
					}
					}
				}
			}
		}
		request.setAttribute("staffList", staffList);
		return "createTask";
	}
	
	@RequestMapping(value="/manager/createTask.htm", method=RequestMethod.POST)
	public String persistTask(@ModelAttribute("task") Task savetask, HttpServletRequest request) throws Exception{
		HttpSession servletSession = request.getSession();
		User u = (User) servletSession.getAttribute("loggedInUser");
		
		savetask.setProject(task.getProject());
		savetask.setTitle(request.getParameter("title"));
		savetask.setAssignedTo(request.getParameter("staff"));
		savetask.setCreatedBy(u.getEmail());
		savetask.setCompletetionLevel("New Task");
		savetask.setDescription(request.getParameter("description"));
//		System.out.println(task.getDescription()+task.getAggignedTo().getUser().getFirstName()+task.getCompletetionLevel());
		
		taskDAO.create(savetask);
		
		return "redirect:/manager/manageTask.htm";
		
	}
	
	/*
	 * The following methods are for managing Issues
	 */
	
	@RequestMapping(value="/manager/manageIssue.htm", method=RequestMethod.GET)
	public String manageIssue(HttpServletRequest request, Model model){
		model.addAttribute("issue", issue);
		//code to get all the task and display in the table
		HttpSession servletSession = request.getSession();
		User u = (User) servletSession.getAttribute("loggedInUser");
		
		List<Issue> issues = new ArrayList<Issue>();
		
		List<Project> projects =  projectDAO.search(u.getCompany());
		for(int i=0; i<projects.size(); i++){
			if(issueDAO.searchByProject(projects.get(i)) != null){
				List<Issue> temp = issueDAO.searchByProject(projects.get(i));
				for(int j=0; j<temp.size(); j++){
					issues.add(temp.get(j));
				}
			}
		}
		request.setAttribute("issues", issues);
		return "manageIssue";
	}
	
	
	/*
	 * Select Project to which you want to add the Issue
	 */
	@RequestMapping(value="/manager/selectProjForIssue.htm", method=RequestMethod.GET)
	public String selectProjectForIssue(HttpServletRequest request){
		HttpSession servletSession = request.getSession();
		User u = (User) servletSession.getAttribute("loggedInUser");
		
		List<Project> projects = projectDAO.search(u.getCompany());
		
		request.setAttribute("projects", projects);
		return "selectProjForIssue";
	}
	
	@RequestMapping(value="/manager/selectProjForIssue.htm", method=RequestMethod.POST)
	public String setProjectToIssue(HttpServletRequest request){
		//look from project based on project name and set it to task
		String projName = request.getParameter("projName");
		Project selectedProj = projectDAO.searchProject(projName);
		issue.setProject(selectedProj);
		return "redirect:/manager/createIssue.htm";
	}
	
	@RequestMapping(value="/manager/createIssue.htm", method=RequestMethod.GET)
	public String createIssue(Model model, HttpServletRequest request) throws Exception{
		model.addAttribute("issue", issue);
		HttpSession servletSession = request.getSession();
		User u = (User) servletSession.getAttribute("loggedInUser");
		
		List<Team> teamList = teamDAO.searchByCompany(u.getCompany());
	
		
		//all staff belonging to the teams in the project are added to staffList
		List<Staff> staffList = new ArrayList<Staff>();
		for(int i=0; i<teamList.size(); i++){
			//if the team has a project
			if(teamList.get(i).getProject() != null){
				//and the project in the team is same as the project name we selected in the previous step
				if(teamList.get(i).getProject().getProjectName().equals(issue.getProject().getProjectName())){
					if(staffDAO.searchByTeam(teamList.get(i)) != null){
					List<Staff> temp =  staffDAO.searchByTeam(teamList.get(i));
					for(int j=0; j<temp.size(); j++){
						staffList.add(temp.get(j));
					}
					}
				}
			}
		}
		request.setAttribute("staffList", staffList);
		
		return "createIssue";
	}
	
	@RequestMapping(value="/manager/createIssue.htm", method=RequestMethod.POST)
	public String persistIssue(HttpServletRequest request, @ModelAttribute("issue") Issue saveIssue){
		HttpSession servletSession = request.getSession();
		User u = (User) servletSession.getAttribute("loggedInUser");
		
		saveIssue.setAssignedTo(request.getParameter("staff"));
		saveIssue.setCreatedBy(u.getEmail());
		saveIssue.setDescription(request.getParameter("description"));
		saveIssue.setTitle(request.getParameter("title"));
		saveIssue.setPriority(request.getParameter("priority"));
		saveIssue.setStatus(request.getParameter("status"));
		saveIssue.setProject(issue.getProject());
		issueDAO.create(saveIssue);
		return "redirect:/manager/manageIssue.htm";
	}
	
	/*
	 * Deleting selected task
	 */
	@RequestMapping(value="/manager/deleteTask.htm", method=RequestMethod.POST )
	public String managerDeleteTask(HttpServletRequest request){
		//Code to delete Issue
		String id = request.getParameter("workQueueID");
		System.out.println(id);
		int workQueueID = Integer.parseInt(id);
		taskDAO.deleteTask(workQueueID);
		return "redirect:/manager/manageTask.htm";
	}
	
	@RequestMapping(value="/manager/deleteIssue.htm", method=RequestMethod.POST )
	public String deleteIssue(HttpServletRequest request){
		//Code to delete Issue
		String id = request.getParameter("workQueueID");
		System.out.println(id);
		int workQueueID = Integer.parseInt(id);
		issueDAO.deleteIssue(workQueueID);
		return "redirect:/manager/manageIssue.htm";
	}
	
	

	/*
	 * Method for deleting and editing Tasks by manager
	 */
	@RequestMapping(value="/manager/editTask.htm", method=RequestMethod.GET)
	public String processEditTaskManager(HttpServletRequest request){
		if(request.getParameter("workQueueID") != null){
			//edit function
			int wqid = Integer.parseInt(request.getParameter("workQueueID"));
			Task requiredTask = taskDAO.searchByID(wqid);
			request.setAttribute("description", requiredTask.getDescription());
			request.setAttribute("workQueueID", request.getParameter("workQueueID"));
			return "editTaskManager";
		}
		if(request.getParameter("wqid") != null){
			//delete function
			String id = request.getParameter("wqid");
			System.out.println(id);
			int workQueueID = Integer.parseInt(id);
			taskDAO.deleteTask(workQueueID);
			return "redirect:/manager/manageTask.htm";
		} 
		else return "redirect:/manager/manageTask.htm";
		
	}
	
	@RequestMapping(value="/manager/editTask.htm", method=RequestMethod.POST )
	public String editTaskManager(HttpServletRequest request, @ModelAttribute("task") Task task){
		
		System.out.println(request.getParameter("workQueueID"));
		System.out.println(request.getParameter("wqid"));
		int wqid = Integer.parseInt(request.getParameter("workQueueID"));
		taskDAO.editTask(request.getParameter("completetionLevel"),wqid);
		return "redirect:/manager/manageTask.htm";
	}
	
	
	/*
	 * Method for deleting and editing issues by manager
	 */
	@RequestMapping(value="/manager/editIssue.htm", method=RequestMethod.GET )
	public String editIssue(HttpServletRequest request){
		System.out.println(request.getParameter("workQueueID"));
		System.out.println(request.getParameter("wqid"));
		
		if(request.getParameter("workQueueID") != null){
			//edit function
			int wqid = Integer.parseInt(request.getParameter("workQueueID"));
			Issue requiredIssue = issueDAO.searchByID(wqid);
			request.setAttribute("description", requiredIssue.getDescription());
			request.setAttribute("workQueueID", request.getParameter("workQueueID"));
			return "editIssueManager";
		}
		if(request.getParameter("wqid") != null){
			//delete function
			String id = request.getParameter("wqid");
			System.out.println(id);
			int workQueueID = Integer.parseInt(id);
			issueDAO.deleteIssue(workQueueID);
			return "redirect:/manager/manageIssue.htm";
		} 
		else return "redirect:/manager/manageIssue.htm";
		
	}
	
	@RequestMapping(value="/manager/editIssue.htm", method=RequestMethod.POST )
	public String processEditIssue(HttpServletRequest request){
		int wqid = Integer.parseInt(request.getParameter("workQueueID"));
		issueDAO.editIssue(request.getParameter("description"), request.getParameter("priority"), request.getParameter("status"),wqid );
	return "redirect:/manager/manageIssue.htm";
	}
	
	
	
	@RequestMapping(value="/manager/myTask.htm", method=RequestMethod.GET)
	public String displayMyTasks(HttpServletRequest request){
		HttpSession servletSession = request.getSession();
		User u = (User) servletSession.getAttribute("loggedInUser");
		
		Staff s = staffDAO.searchByUser(u);
		Project p = s.getTeam().getProject();
		
		List<Task> tasks = new ArrayList<Task>();
		List<Task> projectTasks =  taskDAO.searchByProject(p);
		for(int i=0; i<projectTasks.size(); i++){
			if(projectTasks.get(i).getAssignedTo().equals(u.getEmail())){
				tasks.add(projectTasks.get(i));
			}
		}
		request.setAttribute("tasks", tasks);
		return "myTasks";
	}
	
	
	
	@RequestMapping(value="/manager/myIssue.htm", method=RequestMethod.GET )
	public String displayMyIssue(HttpServletRequest request){
		HttpSession servletSession = request.getSession();
		User u = (User) servletSession.getAttribute("loggedInUser");
		staffDAO.searchByUser(u);
		
		Staff s = staffDAO.searchByUser(u);
		Project p = s.getTeam().getProject();
		
		List<Issue> issues = new ArrayList<Issue>();
		List<Issue> projectIssues =  issueDAO.searchByProject(p);
		for(int i=0; i<projectIssues.size(); i++){
			if(projectIssues.get(i).getAssignedTo().equals(u.getEmail())){
				issues.add(projectIssues.get(i));
			}
		}
		request.setAttribute("issues", issues);
		return "myIssues";
	}
	
	
	/*
	 * Manager can edit and delete projects
	 * The following methods give us that functionality
	 */
	
	@RequestMapping(value="/manager/manageProject.htm", method=RequestMethod.POST)
	public String editProject(HttpServletRequest request, @ModelAttribute("project") Project project){
		System.out.println(request.getParameter("projectID"));
		System.out.println(request.getParameter("pid"));
		
		if(request.getParameter("projectID") != null){
			//edit
			int pid = Integer.parseInt(request.getParameter("projectID"));
			Project p = projectDAO.searchByID(pid);
			List<Team>pTeams =  p.getTeams();
			request.setAttribute("pTeams", pTeams);
			request.setAttribute("projectID", request.getParameter("projectID"));
			return "editProject";
		}
		if(request.getParameter("pid") != null){
			//View Project
			int pid = Integer.parseInt(request.getParameter("pid"));
			Project p = projectDAO.searchByID(pid);
			request.setAttribute("p", p);
			List<Team>pTeams =  p.getTeams();
			request.setAttribute("pTeams", pTeams);
			
			return "projectReport";
		}
		return "redirect:/manager/manageProject.htm";
	}
	
	//update changes made to project
	@RequestMapping(value="/manager/updateProject.htm", method=RequestMethod.POST )
	public String updateProjectChanges(HttpServletRequest request){
		
		int pid = Integer.parseInt(request.getParameter("projectID"));
		projectDAO.updateProj(pid, request.getParameter("stage"), request.getParameter("description"));
		return "redirect:/manager/manageProject.htm";
	}

	
	
}
