package com.job.portal.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.job.portal.dao.EmployerDAO;
import com.job.portal.misc.RetriveLoggedInUser;
import com.job.portal.pojo.Employer;
import com.job.portal.pojo.User;

@Controller
@RequestMapping("/y400z420/godfather.htm")
public class AdminDashboard {

	public AdminDashboard() {

	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView loadDashboard(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		User user = (User) request.getSession().getAttribute("hibuser");
		if (user == null) {
			RetriveLoggedInUser retriveUserName = new RetriveLoggedInUser();
			User hibuser = retriveUserName.getPrincipal();
			user = hibuser;
			request.getSession().setAttribute("hibuser", hibuser);
		}
		EmployerDAO employerDAO = new EmployerDAO();
		List<Employer> employers = employerDAO.displayEmployerList();
		mav.addObject("employers", employers);
		mav.setViewName("godfather");
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView replytoAJAX(HttpServletResponse response, @RequestParam("employerid") String employerid,@RequestParam("statusId") String statusId)
			throws NumberFormatException, Exception {

		ModelAndView mav = new ModelAndView();
		EmployerDAO employerDAO = new EmployerDAO();
		if (employerid != null) {

			int result = employerDAO.appproveOrRejectEmployer(Integer.parseInt(employerid),Integer.parseInt(statusId));
			PrintWriter out = response.getWriter();
			out.print(result);

			return null;

		}
		return mav;
	}

}
