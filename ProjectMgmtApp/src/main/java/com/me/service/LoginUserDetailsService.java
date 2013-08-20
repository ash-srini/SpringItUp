package com.me.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.me.dao.UserDAO;




@Transactional(readOnly = true)
public class LoginUserDetailsService implements UserDetailsService {
	
	protected static Logger logger = Logger.getLogger("service");

	@Autowired
	UserDAO userDAO;
	
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException, DataAccessException {
		
		// Declare a null Spring User
		UserDetails springUser= null;
		
		try {
			
			// Search database for a user that matches the specified username
			// You can provide a custom DAO to access your persistence layer
			// Or use JDBC to access your database
			// DbUser is our custom domain user. This is not the same as Spring's User
			com.me.pojo.User userPojo = userDAO.search(email);
			
			// Populate the Spring User object with details from the dbUser
			// Here we just pass the username, password, and access level
			// getAuthorities() will translate the access level to the correct role type

			springUser =  new User(
					userPojo.getEmail(),
					userPojo.getPassword(),
					true,
					true,
					true,
					true,
					getAuthorities(userPojo.getRole()) );

		} catch (Exception e) {
			logger.error("Error in retrieving user");
			throw new UsernameNotFoundException("Error in retrieving user");
		}
		
		// Return user to Spring for processing.
		// Take note we're not the one evaluating whether this user is authenticated or valid
		// We just merely retrieve a user that matches the specified username
		return springUser;
	}
	
	/**
	 * Retrieves the correct ROLE type depending on the access level, where access level is an Integer.
	 * Basically, this interprets the access value whether it's for a regular user or admin.
	 * 
	 * @param access an integer value representing the access of the user
	 * @return collection of granted authorities
	 */
	 public Collection<GrantedAuthority> getAuthorities(String role) {
			// Create a list of grants for this user
			List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);
			
			// All users are granted with ROLE_USER access
			// Therefore this user gets a ROLE_USER by default
			logger.debug("Grant ROLE_USER to this user");
//			authList.add(new GrantedAuthorityImpl("ROLE_USER"));
			
			// Check if this user has admin access 
			// We interpret Integer(1) as an admin user
			if ( role.equals("CompanyAdmin")) {
				// User has admin access
				logger.debug("Grant ROLE_ADMIN to this user");
				authList.add(new GrantedAuthorityImpl("ROLE_ADMIN"));
			}
			
			else if(role.equals("Manager"))
			{
				logger.debug("Grant ROLE_MANAGER to this user");
				authList.add(new GrantedAuthorityImpl("ROLE_MANAGER"));
				
			}else if(role.equals("Employee")){
				logger.debug("Grant ROLE_EMP to this user");
				authList.add(new GrantedAuthorityImpl("ROLE_EMP"));
			}
			// Return list of granted authorities
			return authList;
	  }
}
