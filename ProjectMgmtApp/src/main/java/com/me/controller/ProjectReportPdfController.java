package com.me.controller;


import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.me.dao.ProjectDAO;
import com.me.dao.TeamDAO;
import com.me.pojo.Project;
import com.me.pojo.Team;

@Controller
public class ProjectReportPdfController {

	@Autowired
	private ProjectDAO projectDAO;
	@Autowired
	private TeamDAO teamDAO;
	
	@RequestMapping(value="/manager/report.pdf", method=RequestMethod.GET)
	public ModelAndView doProcess(Locale locale, ModelMap model, HttpServletRequest request) {
		
		int p = Integer.parseInt(request.getParameter("project")) ;
		Project project = projectDAO.searchByID(p);
		model.addAttribute("project", project);
		
		List<Team> pteams = teamDAO.projTeams(project);
		model.addAttribute("pteams", pteams);
		return new ModelAndView(new MyPdfView(), model);
	}
	
}
