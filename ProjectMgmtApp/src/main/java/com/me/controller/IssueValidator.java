package com.me.controller;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.me.pojo.Issue;

@Service
public class IssueValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Issue.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		Issue newIssue = (Issue) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.invalid.title", "*Title Required");
		//ValidationUtils.rejectIfEmptyOrWhitespace();
	}

}
