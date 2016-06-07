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
import com.job.portal.validator.ApplyJobsValidator;

@Controller
@RequestMapping("/jobseeker/aboutjob.htm")
public class AboutJobController {

	public AboutJobController() {

	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView aboutJob(@RequestParam("jobid") String appliedjobId, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		try {
			int testJobId = Integer.parseInt(appliedjobId);
			JobDAO jobDAO = new JobDAO();
			Job job = jobDAO.getJob(appliedjobId);
			if (job == null) {
				List<Job> jobs = jobDAO.getLatestFiveJobs();
				mav.addObject("jobs", jobs);
				mav.addObject("invalid", true);
				mav.setViewName("jobseekerdashboard");
				return mav;
			}
			mav.addObject("job", job);
			request.getSession().setAttribute("job", job);
			User user = (User) request.getSession().getAttribute("hibuser");
			if (user == null) {
				RetriveLoggedInUser retriveUserName = new RetriveLoggedInUser();
				User hibuser = retriveUserName.getPrincipal();
				user = hibuser;
				request.getSession().setAttribute("hibuser", hibuser);
			}
			boolean check = false;
			if (user != null) {
				AppliedJobDAO appliedJobDAO = new AppliedJobDAO();
				check = appliedJobDAO.checkAlreayApplied(user.getJobseeker().getJobSeekerId(), job.getJobId());
			}
			mav.addObject("check", check);
			mav.setViewName("aboutjob");
			return mav;
		} catch (NumberFormatException e) {
			JobDAO jobDAO = new JobDAO();
			List<Job> jobs = jobDAO.getLatestFiveJobs();
			mav.addObject("jobs", jobs);
			mav.addObject("invalid", true);
			mav.setViewName("jobseekerdashboard");
			return mav;
		}
	}
}
