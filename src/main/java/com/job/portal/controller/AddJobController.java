package com.job.portal.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.job.portal.dao.JobDAO;
import com.job.portal.dao.JobTypeDAO;
import com.job.portal.dao.UserDAO;
import com.job.portal.misc.RetriveLoggedInUser;
import com.job.portal.pojo.Employer;
import com.job.portal.pojo.Job;
import com.job.portal.pojo.Jobtype;
import com.job.portal.pojo.User;
import com.job.portal.validator.AddJobValidator;

@Controller
@RequestMapping("/employer/addjob.htm")
public class AddJobController {

//	AddJobValidator addJobValidator;
//
//	@Autowired
//	public AddJobController(AddJobValidator addJobValidator) {
//		// TODO Auto-generated constructor stub
//		addJobValidator = this.addJobValidator;
//	}

//	@InitBinder
//	private void initBinder(WebDataBinder dataBinder) {
//		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true, 10));
//		dataBinder.setValidator(addJobValidator);
//	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView initializeForm(@ModelAttribute("job") @Valid Job job, BindingResult result, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		boolean check = checkAccess(request);
		if (!check) {
			mav.setViewName("employerdashboard");
			mav.addObject("check", check);
			return mav;
		}

		JobTypeDAO jobTypeDAO = new JobTypeDAO();
		List<Jobtype> jobTypeList = jobTypeDAO.getJobTypes();
		mav.setViewName("addjob");
		mav.addObject("jobTypeList", jobTypeList);
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doSubmitAction(@ModelAttribute("job") @Valid Job job, BindingResult result,
			HttpServletRequest request) throws Exception {

		ModelAndView mav = new ModelAndView();
//		addJobValidator.validate(job, result);
		if (result.hasErrors()) {
			mav.setViewName("addjob");
			return mav;
		}
		User user = (User) request.getSession().getAttribute("hibuser");
		if (user == null) {
			RetriveLoggedInUser retriveUserName = new RetriveLoggedInUser();
			User hibuser = retriveUserName.getPrincipal();
			user = hibuser;
			request.getSession().setAttribute("hibuser", hibuser);
		}
		job.setEmployer(user.getEmployer());
		JobDAO jobDAO = new JobDAO();
		Job savedJob = jobDAO.createJob(job);
		mav.addObject("addedjob", "addedjob");
		mav.setViewName("employerdashboard");
		return mav;
	}

	public boolean checkAccess(HttpServletRequest request) {
		boolean check = false;
		User user = (User) request.getSession().getAttribute("hibuser");
		if (user == null) {
			RetriveLoggedInUser retriveUserName = new RetriveLoggedInUser();
			User hibuser = retriveUserName.getPrincipal();
			user = hibuser;
			request.getSession().setAttribute("hibuser", hibuser);
		}
		if (user != null) {
			UserDAO userDAO = new UserDAO();
			User u = userDAO.getUser(user.getUserId());
			if (u.getEmployer().getAdminapprovalstatuscode().getStatus().equals("Approved")) {
				check = true;
			}
		}

		return check;
	}

}
