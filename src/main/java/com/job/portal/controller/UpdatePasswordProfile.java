package com.job.portal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.job.portal.dao.JobSeekerDAO;
import com.job.portal.misc.RetriveLoggedInUser;
import com.job.portal.pojo.JobSeeker;
import com.job.portal.pojo.User;
import com.job.portal.validator.UpdateProfileValidator;

@Controller
public class UpdatePasswordProfile {
//	UpdateProfileValidator updateProfileValidator;
//	
//	@Autowired
//	public UpdatePasswordProfile(UpdateProfileValidator updateProfileValidator) {
//		// TODO Auto-generated constructor stub
//		updateProfileValidator = this.updateProfileValidator;
//	}
//	
//	@RequestMapping(value = "/updateprofile.htm", method = RequestMethod.GET)
//	public String updateProfile(@ModelAttribute("jobSeeker") JobSeeker jobSeeker, BindingResult result,
//			HttpServletRequest request) {
//		User user = (User) request.getSession().getAttribute("hibuser");
//		if (user == null) {
//			RetriveLoggedInUser retriveUserName = new RetriveLoggedInUser();
//			User hibuser = retriveUserName.getPrincipal();
//			user = hibuser;
//			if (user != null) {
//				request.getSession().setAttribute("hibuser", hibuser);
//			}
//		}
//		return "updateprofile";
//	}
//
//	@RequestMapping(value = "/updateprofile.htm", method = RequestMethod.POST)
//	public ModelAndView update(@ModelAttribute("jobSeeker") JobSeeker jobSeeker, BindingResult result,
//			HttpServletRequest request) {
//		User user = (User) request.getSession().getAttribute("hibuser");
//		ModelAndView mav = new ModelAndView();
//		jobSeeker.getUser().setEmailId(user.getEmailId());
//		jobSeeker.getUser().setPassword(user.getPassword());
//		updateProfileValidator.validate(jobSeeker, result);
//		if (result.hasErrors()) {
//			mav.setViewName("updateprofile");
//			return mav;
//		}
//		JobSeekerDAO jobSeekerDAO = new JobSeekerDAO();
//		boolean check = jobSeekerDAO.updateProfile(jobSeeker);
//		if (check) {
//			mav.addObject("update", "update");
//			mav.setViewName("updateprofile");
//			return mav;
//		}
//		return null;
//	}
}
