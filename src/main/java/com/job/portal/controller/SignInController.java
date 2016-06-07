package com.job.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.job.portal.dao.UserDAO;
import com.job.portal.misc.RetriveLoggedInUser;
import com.job.portal.pojo.User;
import com.job.portal.validator.SignInUserValidator;

@Controller
public class SignInController {

	@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
	protected String homePage(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("hibuser");
		if (user == null) {
			RetriveLoggedInUser retriveUserName = new RetriveLoggedInUser();
			User hibuser = retriveUserName.getPrincipal();
			user = hibuser;
			if (user != null) {
				request.getSession().setAttribute("hibuser", hibuser);
			}
		}
		return "home";
	}

	@RequestMapping(value = "/login.htm", method = RequestMethod.GET)
	protected String initializeForm(@ModelAttribute("user") @Valid User user, BindingResult result) {
		return "signin";
	}

	@RequestMapping(value = "/homepage.htm", method = RequestMethod.GET)
	protected String redirectBasedOnRole(HttpServletRequest request) {
		RetriveLoggedInUser retriveUserName = new RetriveLoggedInUser();
		User hibuser = retriveUserName.getPrincipal();
		request.getSession().setAttribute("hibuser", hibuser);
		if (hibuser.getRoles().getRole().equals("Employer")) {
			return "redirect:employer/employerdashboard.htm";
		} else if (hibuser.getRoles().getRole().equals("JobSeeker")) {
			return "redirect:jobseeker/jobseekerdashboard.htm";
		} else if (hibuser.getRoles().getRole().equals("Admin")) {
			return "redirect:y400z420/godfather.htm";
		}
		return null;
	}

	@RequestMapping(value = "/forgotpassword.htm", method = RequestMethod.GET)
	protected String initializeForgotPasswordForm(@ModelAttribute("user") User user, BindingResult result) {
		return "forgotpassword";
	}

	@RequestMapping(value = "/forgotpassword.htm", method = RequestMethod.POST)
	protected ModelAndView doForgotPasswordAction(@ModelAttribute("user") User user, BindingResult result,
			HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("forgotpassword");
		// signInUserAccountValidator.validate(user, result);
		if (user.getEmailId().trim().isEmpty()) {
			return mav;
		}

		UserDAO userDAO = new UserDAO();
		User hibuser = userDAO.validEmailAddress(user);
		if (hibuser == null) {
			result.rejectValue("emailId", "error.invalid.emailId", "Not a registered Email Address!");
			return mav;
		}

		boolean check = userDAO.generateNewPassword(user);
		if (check) {
			mav.addObject("check", check);
			return mav;
		} else {
			return null;
		}

	}

}
