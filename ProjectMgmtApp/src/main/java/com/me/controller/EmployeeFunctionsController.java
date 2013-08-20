package com.me.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.me.dao.IssueDAO;
import com.me.dao.StaffDAO;
import com.me.dao.TaskDAO;
import com.me.pojo.Issue;
import com.me.pojo.Project;
import com.me.pojo.Staff;
import com.me.pojo.Task;
import com.me.pojo.User;

@Controller
public class EmployeeFunctionsController {
	
	@Autowired
	private StaffDAO staffDAO;
	@Autowired
	private TaskDAO taskDAO;
	@Autowired
	private IssueDAO issueDAO;
	
	@Autowired
	private Task task;
	
	/*
	 * goes to the my Task views 
	 */
	@RequestMapping(value="/employee/myTask.htm", method=RequestMethod.GET)
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
	
	
	
	@RequestMapping(value="/employee/myIssue.htm", method=RequestMethod.GET )
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
	
	@RequestMapping(value="/employee/deleteIssue.htm", method=RequestMethod.POST )
	public String deleteIssue(HttpServletRequest request){
		//Code to delete Issue
		String id = request.getParameter("workQueueID");
		System.out.println(id);
		int workQueueID = Integer.parseInt(id);
		issueDAO.deleteIssue(workQueueID);
		return "redirect:/employee/myIssue.htm";
	}
	
	@RequestMapping(value="/employee/editTask.htm", method=RequestMethod.GET )
	public String editTask(HttpServletRequest request, Model model){
		System.out.println(request.getParameter("workQueueID"));
		request.setAttribute("workQueueID", request.getParameter("workQueueID"));
		model.addAttribute("task", task);
		return "editTask";
	}

	@RequestMapping(value="/employee/editTask.htm", method=RequestMethod.POST )
	public String processEditTask(HttpServletRequest request, @ModelAttribute("task") Task task){
		int wqid = Integer.parseInt(request.getParameter("workQueueID"));
		taskDAO.editTask(request.getParameter("completetionLevel"),wqid );
		
		return "redirect:/employee/myTask.htm";
	}
	
	
	@RequestMapping(value="/employee/editIssue.htm", method=RequestMethod.GET )
	public String editIssue(HttpServletRequest request){
		System.out.println(request.getParameter("workQueueID"));
		System.out.println(request.getParameter("wqid"));
		
		if(request.getParameter("workQueueID") != null){
			//edit function
			int wqid = Integer.parseInt(request.getParameter("workQueueID"));
			Issue requiredIssue = issueDAO.searchByID(wqid);
			request.setAttribute("description", requiredIssue.getDescription());
			request.setAttribute("workQueueID", request.getParameter("workQueueID"));
			return "editIssue";
		}
		if(request.getParameter("wqid") != null){
			//delete function
			String id = request.getParameter("wqid");
			System.out.println(id);
			int workQueueID = Integer.parseInt(id);
			issueDAO.deleteIssue(workQueueID);
			return "redirect:/employee/myIssue.htm";
		} 
		else return "redirect:/employee/myIssue.htm";
		
	}
	
	@RequestMapping(value="/employee/editIssue.htm", method=RequestMethod.POST)
	public String processEditIssue(HttpServletRequest request){
		
		int wqid = Integer.parseInt(request.getParameter("workQueueID"));
		
		issueDAO.editIssue(request.getParameter("description"), request.getParameter("priority"), request.getParameter("status"),wqid );
		return "redirect:/employee/myIssue.htm";
	}
}
