package com.me.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.me.dao.AddressDAO;
import com.me.dao.CompanyDAO;
import com.me.dao.PaymentInfoDAO;
import com.me.dao.StaffDAO;
import com.me.dao.TeamDAO;
import com.me.dao.UserDAO;
import com.me.pojo.Address;
import com.me.pojo.Company;
import com.me.pojo.PaymentInfo;
import com.me.pojo.Staff;
import com.me.pojo.Team;
import com.me.pojo.User;

/**
 * This controller handles users login 
 */
@Controller
public class HomeController {
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws Exception 
	 */
	@Autowired
	private User user;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private StaffDAO staffDAO;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, @ModelAttribute("user") User user, @ModelAttribute("address") Address address, @ModelAttribute("company") Company company, @ModelAttribute("paymentInfo") PaymentInfo paymentInfo) throws Exception {
		
		model.addAttribute("user",user);
		model.addAttribute("company",company);
		model.addAttribute("address",address);
		model.addAttribute("paymentInfo", paymentInfo);
		
		return "home";
	}
	
	@RequestMapping(value="/register.htm", method=RequestMethod.GET)
	public String register(Model model,@ModelAttribute("address") Address address, @ModelAttribute("user") User user, @ModelAttribute("company") Company company, @ModelAttribute("paymentInfo") PaymentInfo paymentInfo) throws Exception{
		
		//Address values hard coded
		address.setCity("Boston");
		address.setCountry("USA");
		address.setState("MA");
		address.setStreetName("Westland Ave");
		
		//Payment info hard coded
		paymentInfo.setCardType("Visa");
		paymentInfo.setCreditCardNumber("1234234534564567");
		paymentInfo.setCvcNumber("111");
		paymentInfo.setExpirationDate("02/17");
		
		//company values hard coded
		company.setAddress(address);
		company.setNumOfEmployees("20");
		company.setCompanyName("mycompany");
		company.setPaymentInfo(paymentInfo);
		
		//hard coded values of user
		user.setFirstName("ash");
		user.setLastName("srini");
		user.setCompany(company);
		user.setDob("12/4/89");
		user.setEmail("ash@mycompany.com");
		user.setPassword("password");
		user.setJobTitle("admin");
		user.setRole("CompanyAdmin");
		user.setPhone("617-555-0000");
		
		//company.newEmployeeList(user);
		
		AddressDAO addressdao = new AddressDAO();
		UserDAO userDao = new UserDAO();
		CompanyDAO companyDao = new CompanyDAO();
		PaymentInfoDAO paymentInfoDAO = new PaymentInfoDAO();
		System.out.println(address.getAddressID()+address.getCity());
		
		addressdao.create(address);
		paymentInfoDAO.create(paymentInfo);
		companyDao.create(company);
		userDao.create(user);

		
		
		return "registeration";
	}
	
	@RequestMapping(value="/signin.htm", method=RequestMethod.GET)
	public String showSignInPage(Model model, @RequestParam(value="error", required=false) boolean error,HttpServletRequest request){
		
		if(error==true){
			request.setAttribute("error", "Invalid Username/Password ");
		}
		else{
			request.setAttribute("error", "");
		}
		model.addAttribute("user", user);
		return "signin";
	}
	
	 @RequestMapping(value = "/denied.htm", method = RequestMethod.GET)
	  public String getDeniedPage() {
	  
	 
	  return "userNotFound";
	 }
	
	@RequestMapping(value="/processSignIn.htm", method=RequestMethod.GET)
	public String processSignIn( @ModelAttribute("user") @Validated User user, HttpServletRequest request, BindingResult result) throws Exception{


		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String email =  auth.getName();
		HttpSession servletSession = request.getSession();
		System.out.println(email);
		User rs = userDAO.search(email);
		
		if(rs.getRole().equals("CompanyAdmin")){
			
			//User who is logged in is saved in the session for future reference
			servletSession.setAttribute("loggedInUser", rs);
			servletSession.setAttribute("companyName", rs.getCompany().getCompanyName());
			return "adminLogin";
		}else {
			if(rs.getRole().equals("Manager")){
				
				//User who is logged in is saved in the session for future reference
				servletSession.setAttribute("loggedInUser", rs);
				servletSession.setAttribute("companyName", rs.getCompany().getCompanyName());
				return "managerLogin";
			}else{
				if(rs.getRole().equals("Employee")){
					
					//User who is logged in is saved in the session for future reference
					servletSession.setAttribute("loggedInUser", rs);
					servletSession.setAttribute("companyName", rs.getCompany().getCompanyName());
					
					servletSession.setAttribute("stafflogin", staffDAO.searchByUser(rs));
					
					return "employeeLogin";
				
			}
		}
		
		return "userNotFound";
		}
	}
		//check email and password
	/*	String tempEmail = user.getEmail();
		String tempPassword = user.getPassword().toString();
		
		//get user from database by passing tempEmail and tempPassword
		
		//User rs =  userDAO.searchUser(tempEmail, tempPassword, user);
		
		if(!rs.equals(user)){
			if(rs.getPassword().equals(tempPassword)){
				//check role
				if(rs.getRole().equals("CompanyAdmin")){
					HttpSession servletSession = request.getSession();
					//User who is logged in is saved in the session for future reference
					servletSession.setAttribute("loggedInUser", rs);
					servletSession.setAttribute("companyName", rs.getCompany().getCompanyName());
					return "adminLogin";
				}else{
				if(rs.getRole().equals("Manager")){
					HttpSession servletSession = request.getSession();
					//User who is logged in is saved in the session for future reference
					servletSession.setAttribute("loggedInUser", rs);
					servletSession.setAttribute("companyName", rs.getCompany().getCompanyName());
					return "managerLogin";
				}else{
				if(rs.getRole().equals("Employee")){
					HttpSession servletSession = request.getSession();
					//User who is logged in is saved in the session for future reference
					servletSession.setAttribute("loggedInUser", rs);
					servletSession.setAttribute("companyName", rs.getCompany().getCompanyName());
					if( staffDAO.searchByUser(rs) != null){
						servletSession.setAttribute("staff", staffDAO.searchByUser(rs));
					}
					
				
				}return "employeeLogin";
				}
				}
				
			}else{
				return "userNotFound";
			}
		}else{
			return "userNotFound";
		}
*/
	
	
	@RequestMapping(value="/logout.htm", method=RequestMethod.GET)
	public String logout(){
		
		//should end the session
		return"home";
	}

	@RequestMapping(value="/editAccountDetails.htm", method=RequestMethod.GET)
	public String editAccountDetails(Model model){
		model.addAttribute("user", user);
		return"editUserAcc";
	}

	@RequestMapping(value="/editAccountDetails.htm", method=RequestMethod.POST)
	public String processAccountDetails(@ModelAttribute("user") User user,HttpServletRequest request){
		//System.out.println(request.getParameter("oldpassword")+request.getParameter("newpassword")+request.getParameter("confpassword"));
		HttpSession servletSession = request.getSession();
		User u = (User) servletSession.getAttribute("loggedInUser");
		
		if(request.getParameter("newpassword").equals(request.getParameter("confpassword"))){
			if(request.getParameter("oldpassword").equals(u.getPassword())){
				userDAO.resetPassword(u.getUserID(), request.getParameter("newpassword"));
				return "redirect:/editAccountDetails.htm";
			}
		}
		
		return"couldNotReset";
	}
	
	
	
	
}
