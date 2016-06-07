package com.job.portal.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.job.portal.dao.AppliedJobDAO;
import com.job.portal.dao.JobDAO;
import com.job.portal.pojo.Appliedjobs;
import com.job.portal.pojo.Job;
import com.job.portal.pojo.User;
import com.job.portal.validator.ApplyJobsValidator;

@Controller
@RequestMapping("/jobseeker/applyjob.htm")
public class ApplyJobController {

	ApplyJobsValidator appliedJobsValidator;
	// ServletContext context;

	@Autowired
	public ApplyJobController(ApplyJobsValidator appliedJobsValidator) {
		this.appliedJobsValidator = appliedJobsValidator;
		// this.context = context;
	}

	@InitBinder()
	private void initBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
		// dataBinder.setValidator(appliedJobsValidator);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView applyJob(@ModelAttribute("appliedJobs") Appliedjobs appliedJobs, BindingResult result,
			@RequestParam("jobid") String jobId, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		try {
			int checkInt = Integer.parseInt(jobId);
			JobDAO jobDAO = new JobDAO();
			User user = (User) request.getSession().getAttribute("hibuser");
			if (user != null) {
				if (jobDAO.checkIfAlreadyApplied(jobId, user.getUserId())) {

					mav.addObject("check", true);
					mav.setViewName("aboutjob");
					return mav;
				}
			}
			if (null == jobDAO.getJob(jobId)) {
				List<Job> jobs = jobDAO.getLatestFiveJobs();
				mav.addObject("jobs", jobs);
				mav.addObject("invalid", true);
				mav.setViewName("jobseekerdashboard");
				return mav;
			}
			// Job job = jobDAO.getJob(jobId);
			// System.out.println("*** Job: " + job.getJobDescription());
			// mav.addObject("job", job);
			mav.setViewName("applyjob");
			// System.out.println(context.getRealPath(""));
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

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView addAppliedJob(@ModelAttribute("appliedJobs") Appliedjobs appliedJobs, BindingResult result,
			HttpServletRequest request) throws Exception {
		try {
			ModelAndView mav = new ModelAndView();
			appliedJobsValidator.validate(appliedJobs, result);
			if (result.hasErrors()) {
				mav.setViewName("applyjob");
				return mav;
			}
			User user = (User) request.getSession().getAttribute("hibuser");
			saveFile(appliedJobs, user);
			appliedJobs.setJobSeekerId(user.getJobseeker().getJobSeekerId());
			// appliedJobs.setJobseeker(user.getJobseeker());
			AppliedJobDAO applyJobDAO = new AppliedJobDAO();
			applyJobDAO.applyJob(appliedJobs);
			mav.addObject("jobapplied", "jobapplied");
			mav.addObject("check", true);
			mav.setViewName("aboutjob");
			return mav;

		} catch (MaxUploadSizeExceededException e) {

		}
		return null;
	}

	public void saveFile(Appliedjobs appliedJobs, User user) throws IllegalStateException, IOException {
		File file;
		// String path = context.getRealPath("");
		String path = System.getProperty("catalina.home") + "\\uploads\\";
		if (appliedJobs.getResumeFile() != null) {

			String fileNameWithExt = System.currentTimeMillis() + user.getUserId()
					+ appliedJobs.getResumeFile().getOriginalFilename();
			file = new File(path + fileNameWithExt);
			appliedJobs.getResumeFile().transferTo(file);
			appliedJobs.setResumeFileName(path + fileNameWithExt);

		}

	}

}
