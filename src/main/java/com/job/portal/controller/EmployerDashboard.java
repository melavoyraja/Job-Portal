package com.job.portal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.job.portal.dao.UserDAO;
import com.job.portal.misc.RetriveLoggedInUser;
import com.job.portal.pojo.Employer;
import com.job.portal.pojo.User;

@Controller
@RequestMapping("/employer/employerdashboard.htm")
public class EmployerDashboard {

	public EmployerDashboard() {

	}

	@RequestMapping(method = RequestMethod.GET)
	public String loadDashboard(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("hibuser");
		if (user == null) {
			RetriveLoggedInUser retriveUserName = new RetriveLoggedInUser();
			User hibuser = retriveUserName.getPrincipal();
			user = hibuser;
			request.getSession().setAttribute("hibuser", hibuser);
		}
		Employer employer = user.getEmployer();
		return "employerdashboard";
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView dashboard(@RequestParam("id") String id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user1 = (User) request.getSession().getAttribute("hibuser");
		if (user1 == null) {
			RetriveLoggedInUser retriveUserName = new RetriveLoggedInUser();
			User hibuser = retriveUserName.getPrincipal();
			user1 = hibuser;
			request.getSession().setAttribute("hibuser", hibuser);
		}
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUser(user1.getUserId());
		if (id.equals("employerjobs")) {
			mav.setViewName(id);
			mav.addObject("jobs", user.getEmployer().getJobs());
		}
		return mav;
	}

}
