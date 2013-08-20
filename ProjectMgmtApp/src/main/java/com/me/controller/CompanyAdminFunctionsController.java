package com.me.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.property.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.me.dao.StaffDAO;
import com.me.dao.TeamDAO;
import com.me.dao.UserDAO;
import com.me.pojo.Company;
import com.me.pojo.Staff;
import com.me.pojo.Team;
import com.me.pojo.User;
/**
 * This controller handles all the function that a Company Admin has to perform. We knoe if the user is a company admin by checking the role of the logged in user. 
 * Logged in user is saved in the session
 */

@Controller
public class CompanyAdminFunctionsController {
	
	@Autowired
	private User companyUser;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private Team team;
	
	@Autowired
	private Staff staff;

	@Autowired
	private TeamDAO teamDAO;
	
	@Autowired
	private StaffDAO staffDAO;
	
	@Autowired
	private UserValidator userValidator;
	
	@RequestMapping(value="/companyadmin/showAddUser", method=RequestMethod.GET)
	public String showAddUser(Model model, HttpServletRequest request){
		model.addAttribute("companyUser", companyUser);
		
		return "addUser";
	}
	
	/*
	 * This method is used for the Company Admin to add more users to the company
	 * the new users will belong to the same company as the logged in company admin
	 */
	@RequestMapping(value="/companyadmin/createCompanyUser.htm", method=RequestMethod.POST)
	public String createCompanyUser(@ModelAttribute("companyUser") User companyUser, HttpServletRequest request, BindingResult result) throws Exception{
		//get logged in user from session
		HttpSession servletSession = request.getSession();
		User u = (User) servletSession.getAttribute("loggedInUser");
		
		userValidator.validate(companyUser, result);
		if(result.hasErrors() == true){
			return "addUser";
		}
		
		//****ADD CHECK TO SEE IF USER ALREADY EXISTS IN THE COMPANY****
		if(userDAO.search(companyUser.getEmail()) != null){
			return "userExists";
		}
		
		
		//set company for new user same as the logged in user
		companyUser.setCompany(u.getCompany());
		//default password is company name
		companyUser.setPassword(u.getCompany().getCompanyName());
		//save the user to the data base
		userDAO.create(companyUser);
		request.setAttribute("add", "true");
		return "adminLogin";
	}
	
	/*
	 * Adding method to process jQuery AJAX request
	 */
	
	@RequestMapping(value = "/companyadmin/checkUserName", method = RequestMethod.GET)
    public @ResponseBody String checkUserNameExists(@RequestParam("x") String x, HttpServletRequest request) throws Exception {
       if( userDAO.search(x) != null){
    	   return "yes";
       }else {
    	   return "no";
       }
      
        
    }
	
	/*
	 * This method is used for the Company Admin to add more Teams to the company

	 */
	@RequestMapping(value="/companyadmin/createTeam.htm", method=RequestMethod.GET)
	public String createTeam(Model model, HttpServletRequest request){
		model.addAttribute("team", team);
		return "createTeam";
	}
	
	@RequestMapping(value="/companyadmin/createTeam.htm", method=RequestMethod.POST)
	public String addTeam(@ModelAttribute("team") Team team, Model model, HttpServletRequest request) throws Exception{
		
		HttpSession servletSession = request.getSession();
		User u = (User) servletSession.getAttribute("loggedInUser");
		//check if team has been added to company, if it is added return team already added page
		//search company for all existing teams 
		List<Team> existingTeams = teamDAO.searchByCompany(u.getCompany());
		
		//adding team to a company for the first time
		if(existingTeams.size() == 0){
			//add team to db using dao if no team has been added at all
			team.setCompany(u.getCompany());
			
		
			teamDAO.create(team);
			teamDAO.close();
			return "newTeamAdded";
		}
		else{
			
			//teams exist, so check if this one is added already
			for(int i=0; i<existingTeams.size(); i++){
				if(team.getTeamName().equals(existingTeams.get(i).getTeamName())){
					return "teamAdded";
				}
				//team is not added already, so add now
				
			}
			
			team.setCompany(u.getCompany());
			teamDAO.create(team);
			teamDAO.close();
			return "newTeamAdded";
		}
	
		
		}
		
	
	
	
	/*
	 * This method is used for the Company Admin to addStaff to created team 
	 */
	@RequestMapping(value="/companyadmin/addStaff.htm", method=RequestMethod.GET)
	public String showAddStaff(Model model, HttpServletRequest request) throws Exception{
		//set team in request scope so that the add staff page has access to the team created
				//request.setAttribute("team", team);
				
				HttpSession servletSession = request.getSession();
				User u = (User) servletSession.getAttribute("loggedInUser");
				
				//set staff in model
				model.addAttribute("staff", staff);
				
				//get all the user of this particular company and set it in the request scope
				List<User> resultUserList = userDAO.searchByCompany(u.getCompany());
				
//				int count = resultUserList.size();
//				
//				//remove users from list for which staff is already created
//				for(int i=0; i<resultUserList.size(); i++){
//					User tempU = resultUserList.get(i);
//					 boolean flag = staffDAO.searchStaff(tempU);
//					if(flag==true){
//						resultUserList.remove(i);
//					}
//				}
				
				//get all the teams from the company
				List<Team> resultTeamList = teamDAO.searchByCompany(u.getCompany());
				
				request.setAttribute("resultUserList", resultUserList);
				request.setAttribute("resultTeamList", resultTeamList);
				
		return "addStaff";
	}
	
	@RequestMapping(value="/companyadmin/addStaff.htm", method=RequestMethod.POST)
	public String showStaffAddSuccess(Model model, HttpServletRequest request) throws Exception{
		boolean flag = false;
		HttpSession servletSession = request.getSession();
		User u = (User) servletSession.getAttribute("loggedInUser");
		
		// search for user and team based on the name of each
		String email = request.getParameter("user");
		String tn = request.getParameter("team");	
		
		
		
		//got user from user table to link to staff selected
		User resultUser = userDAO.searchByEmail(email, u);
		
		if(staffDAO.searchStaff(resultUser) == true){
			return "staffAssignedToTeam";
		}
		
		
		if(!resultUser.getEmail().equals(u.getEmail())){
			
			staff.setUser(resultUser);
			flag =true;
		}else{
			flag= false;
		}
		
		//got selected team of the company the staff belongs to to link it to the staff selected
		List<Team> existingTeams = teamDAO.searchByCompany(u.getCompany());
		for(int i=0; i<existingTeams.size(); i++){
			Team tempTeam = existingTeams.get(i);
			if(tempTeam.getTeamName().equals(tn)){
				
				//gets the list of staff that belongs to this team
				List<Staff> teamStaffList = tempTeam.getStaffList();
				
				
				
				//adding first staff
				if(teamStaffList.size() == 0){
					staff.setTeam(tempTeam);
					flag =true;
					staffDAO.create(staff);
					return "staffAdded";
				}
				
				//adding staff that already exists in the team
				for(int j=0; j<teamStaffList.size(); j++){
					//
					User s = teamStaffList.get(j).getUser();
					if(teamStaffList.get(j).getUser().getEmail().equals(resultUser.getEmail())){
						return "staffAlreadyAdded";
					}
				}
				
				//adding more new staff to the team
				if(teamStaffList.size() != 0){
				
				staff.setTeam(tempTeam);
				flag=true;
				staffDAO.create(staff);
				return "staffAdded";
				}
			}
			
		}
		return "cannotAddStaff";
		
//		

		
	}
}
