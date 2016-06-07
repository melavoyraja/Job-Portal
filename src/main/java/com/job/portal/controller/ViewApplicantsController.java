package com.job.portal.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
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

public class ViewApplicantsController {

	public ViewApplicantsController() {

	}

	@RequestMapping(value = "/employer/viewapplicants.htm", method = RequestMethod.GET)
	public ModelAndView viewApplicants(@RequestParam("jobid") String jobid, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			int checkInt = Integer.parseInt(jobid);
			mav.setViewName("employerdashboard");
			AppliedJobDAO appliedJobsDAO = new AppliedJobDAO();
			User user = (User) request.getSession().getAttribute("hibuser");
			if (user == null) {
				RetriveLoggedInUser retriveUserName = new RetriveLoggedInUser();
				User hibuser = retriveUserName.getPrincipal();
				user = hibuser;
				request.getSession().setAttribute("hibuser", hibuser);
			}
			JobDAO jobDAO = new JobDAO();
			Job job = jobDAO.getJob(jobid);
			if (job == null) {
				mav.addObject("jobid", null);
				mav.addObject("job", null);
				mav.addObject("invalid", true);
				return mav;
			}
			if (!job.getEmployer().getEmployerId().equals(user.getEmployer().getEmployerId())) {
				mav.addObject("jobid", null);
				mav.addObject("unauth", true);
				mav.addObject("job", null);
				mav.addObject("applicants", null);
				return mav;
			}
			List<Appliedjobs> applicants = appliedJobsDAO.getAppliedJobsListByJobID(Integer.parseInt(jobid));
			mav.addObject("jobid", jobid);
			mav.addObject("job", job);
			mav.addObject("applicants", applicants);
			return mav;
		} catch (NumberFormatException e) {
			mav.addObject("jobid", null);
			mav.addObject("job", null);
			mav.addObject("invalid", true);
			mav.setViewName("employerdashboard");
			return mav;
		}
	}

	@RequestMapping(value = "/employer/viewapplicationdetails.htm", method = RequestMethod.GET)
	public ModelAndView viewApplicantionDetails(@RequestParam("applicationID") String applicationID,
			@RequestParam("jobId") String jobID, HttpServletRequest request) throws IOException {
		ModelAndView mav = new ModelAndView();
		try {
			int testApp = Integer.parseInt(applicationID);
			int testJob = Integer.parseInt(jobID);
			AppliedJobDAO appliedJobsDAO = new AppliedJobDAO();
			Appliedjobs appliedJob = appliedJobsDAO.getAppliedJob(applicationID);
			User user = (User) request.getSession().getAttribute("hibuser");
			if (user == null) {
				RetriveLoggedInUser retriveUserName = new RetriveLoggedInUser();
				User hibuser = retriveUserName.getPrincipal();
				user = hibuser;
				request.getSession().setAttribute("hibuser", hibuser);
			}

			if (appliedJob != null) {
				if (appliedJob.getJobId() == testJob && appliedJob.getJob().getEmployer().getEmployerId()
						.equals(user.getEmployer().getEmployerId())) {
					mav.addObject("applicationID", applicationID);
					mav.addObject("appliedJob", appliedJob);
					mav.setViewName("employerdashboard");
					return mav;
				}
			}
			mav.addObject("applicationID", null);
			mav.addObject("appliedJob", null);
			mav.addObject("invalidapp", true);
			mav.setViewName("employerdashboard");
			return mav;

		} catch (NumberFormatException e) {
			mav.addObject("applicationID", null);
			mav.addObject("appliedJob", null);
			mav.addObject("invalidapp", true);
			mav.setViewName("employerdashboard");
			return mav;
		}
	}

	@RequestMapping(value = "/employer/changeappliedjobstatus.htm", method = RequestMethod.GET)
	public ModelAndView changeApplicationState(@RequestParam("action") String action,
			@RequestParam("jobId") String jobID, @RequestParam("applicationID") String applicationID,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			int testapp = Integer.parseInt(applicationID);
			int testJob = Integer.parseInt(jobID);
			int status = 1;
			if (action != null) {
				if (action.equals("Accept")) {
					status = 3;
				} else if (action.equals("Decline")) {
					status = 2;
				}
			} else {
				mav.addObject("applicationID", null);
				mav.addObject("appliedJob", null);
				mav.addObject("invalidapp", true);
				mav.setViewName("employerdashboard");
				return mav;
			}
			AppliedJobDAO appliedJobDAO = new AppliedJobDAO();

			Appliedjobs appliedJob = appliedJobDAO.getAppliedJob(applicationID);

			User user = (User) request.getSession().getAttribute("hibuser");
			if (user == null) {
				RetriveLoggedInUser retriveUserName = new RetriveLoggedInUser();
				User hibuser = retriveUserName.getPrincipal();
				user = hibuser;
				request.getSession().setAttribute("hibuser", hibuser);
			}

			if (appliedJob != null) {
				if (appliedJob.getJobId() == testJob && appliedJob.getJob().getEmployer().getEmployerId()
						.equals(user.getEmployer().getEmployerId())) {
					boolean check = appliedJobDAO.updateAppliedJobStatus(status, applicationID);
					appliedJob = appliedJobDAO.getAppliedJob(applicationID);
					mav.addObject("appliedJob", appliedJob);
					if (check) {
						mav.addObject("applicationID", applicationID);
						mav.setViewName("employerdashboard");
						return mav;
					}
				}
				mav.addObject("applicationID", null);
				mav.addObject("appliedJob", null);
				mav.addObject("invalidapp", true);
				mav.setViewName("employerdashboard");
				return mav;

			}
		} catch (NumberFormatException e) {
			mav.addObject("applicationID", null);
			mav.addObject("appliedJob", null);
			mav.addObject("invalidapp", true);
			mav.setViewName("employerdashboard");
			return mav;
		}
		return null;
	}

	@RequestMapping(value = "/employer/viewapplicantresume.pdf", method = RequestMethod.GET)
	public ModelAndView viewApplicantResume(@RequestParam("refid") String refid, @RequestParam("jobId") String jobID,
			HttpServletResponse response, HttpServletRequest request) throws IOException {
		ModelAndView mav = new ModelAndView();
		try {
			int applicationID = Integer.parseInt(refid);
			int testJob = Integer.parseInt(jobID);
			// mav.setViewName("displayresume");
			AppliedJobDAO appliedJobsDAO = new AppliedJobDAO();
			Appliedjobs appliedJob = appliedJobsDAO.getAppliedJob(refid);
			User user = (User) request.getSession().getAttribute("hibuser");
			if (user == null) {
				RetriveLoggedInUser retriveUserName = new RetriveLoggedInUser();
				User hibuser = retriveUserName.getPrincipal();
				user = hibuser;
				request.getSession().setAttribute("hibuser", hibuser);
			}
			if (appliedJob == null) {
				mav.addObject("invalidfile", true);
				mav.setViewName("employerdashboard");
				return mav;
			}
			if (appliedJob != null) {
				if (appliedJob.getJobId() == testJob && appliedJob.getJob().getEmployer().getEmployerId()
						.equals(user.getEmployer().getEmployerId())) {
					String fileName = appliedJob.getResumeFileName();
					System.out.println(fileName);
					File pdfFile = new File(fileName);
					byte[] pdfByteArray = FileUtils.readFileToByteArray(pdfFile);
					response.setContentType("application/pdf");
					response.getOutputStream().write(pdfByteArray);
					response.getOutputStream().flush();
					return null;
				}
			}
			mav.addObject("unauth", true);
			mav.setViewName("employerdashboard");
			return mav;
			// return mav;
		} catch (NumberFormatException e) {
			mav.addObject("invalidfile", true);
			mav.setViewName("employerdashboard");
			return mav;
		}

	}

}
