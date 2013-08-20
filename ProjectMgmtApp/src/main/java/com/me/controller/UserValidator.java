package com.me.controller;


import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.pojo.User;
@Service
@SuppressWarnings("rawtypes")
public class UserValidator implements Validator{

	 
	  @Override
	    public void validate(Object obj, Errors errors)
	    {
	        User newUser = (User) obj;

	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.email", "*Email Required");
	      
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "errror.invalid.required","*First Name Required");
	        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "errror.invalid.required","*Last Name Required");
	        if(!newUser.getEmail().contains("@") ){
	        	 errors.rejectValue("email", "UserValidator.email.emailInvalid",
	        	          "*Invalid Email");
	        }
//	        if(newUser.getRole().equals("") || newUser.getRole().equals(null)){
//	        	errors.rejectValue("role", "UserValidator.role.emailInvalid",
//	        	          "*Please select an option");
//	        }
	        
//	        
	    }

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
	}
	  
}
