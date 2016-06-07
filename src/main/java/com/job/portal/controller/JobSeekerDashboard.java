package com.job.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.job.portal.dao.AppliedJobDAO;
import com.job.portal.dao.JobDAO;
import com.job.portal.misc.RetriveLoggedInUser;
import com.job.portal.pojo.Appliedjobs;
import com.job.portal.pojo.Job;
import com.job.portal.pojo.User;

@Controller

public class JobSeekerDashboard {

	public JobSeekerDashboard() {

	}

	@RequestMapping(value="/jobseeker/jobseekerdashboard.htm",method = RequestMethod.GET)
	public ModelAndView loadDashBoard(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		User user = (User) request.getSession().getAttribute("hibuser");
		if (user == null) {
			RetriveLoggedInUser retriveUserName = new RetriveLoggedInUser();
			User hibuser = retriveUserName.getPrincipal();
			user = hibuser;
			request.getSession().setAttribute("hibuser", hibuser);
		}
		JobDAO jobDAO = new JobDAO();
		List<Job> jobs = jobDAO.getLatestFiveJobs();
		mav.addObject("jobs", jobs);
		mav.setViewName("jobseekerdashboard");
		return mav;
	}
	
	@RequestMapping(value="/jobseeker/update.htm",method = RequestMethod.GET)
	public ModelAndView updateProfile(@RequestParam("update") String update,HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		User user = (User) request.getSession().getAttribute("hibuser");
		if (user == null) {
			RetriveLoggedInUser retriveUserName = new RetriveLoggedInUser();
			User hibuser = retriveUserName.getPrincipal();
			user = hibuser;
			request.getSession().setAttribute("hibuser", hibuser);
		}
		JobDAO jobDAO = new JobDAO();
		List<Job> jobs = jobDAO.getLatestFiveJobs();
		mav.addObject("jobs", jobs);
		mav.setViewName("jobseekerdashboard");
		return mav;
	}

	
	
	@RequestMapping(value="/jobseeker/jobseekerdashboard.htm", method = RequestMethod.POST)
	public ModelAndView appliedJobs(@RequestParam("id") String id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		AppliedJobDAO appliedJobsDAO = new AppliedJobDAO();
		if (id.equals("appliedjobs")) {
			User user = (User) request.getSession().getAttribute("hibuser");
			if (user == null) {
				RetriveLoggedInUser retriveUserName = new RetriveLoggedInUser();
				User hibuser = retriveUserName.getPrincipal();
				user = hibuser;
				request.getSession().setAttribute("hibuser", hibuser);
			}
			// user = appliedJobsDAO.getAppliedJobsListByJobSeekerID(user);
			List<Appliedjobs> appliedjobs = appliedJobsDAO
					.getAppliedJobsListByJobSeekerID(user.getJobseeker().getJobSeekerId());
			for (Appliedjobs aj : appliedjobs) {
				System.out.println(aj.getJobApplicationStatusCode().getStatus());
			}

			mav.addObject("appliedjobs", appliedjobs);
			mav.setViewName("jobseekerapplications");
		}

		return mav;
	}

}
