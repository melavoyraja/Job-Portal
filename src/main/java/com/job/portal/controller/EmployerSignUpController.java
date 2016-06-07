package com.job.portal.controller;

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

import com.job.portal.dao.EmployerDAO;
import com.job.portal.dao.UserDAO;
import com.job.portal.pojo.Employer;
import com.job.portal.pojo.User;
import com.job.portal.validator.SignUpEmployerValidator;

@Controller
@RequestMapping("/employersignup.htm")
public class EmployerSignUpController {

	// SignUpEmployerValidator suev;
	//
	// @Autowired
	// public EmployerSignUpController(SignUpEmployerValidator suev) {
	// // TODO Auto-generated constructor stub
	// this.suev = suev;
	// }
	//
	// @InitBinder
	// private void initBinder(WebDataBinder dataBinder) {
	// dataBinder.setValidator(suev);
	// }

	@RequestMapping(method = RequestMethod.GET)
	protected String initializeForm(@ModelAttribute("employer") @Valid Employer employer, BindingResult result) {
		return "employersignup";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doSubmitAction(@ModelAttribute("employer") @Valid Employer employer, BindingResult result)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		// suev.validate(employer, result);
		if (result.hasErrors()) {
			mav.setViewName("employersignup");
			return mav;
		}
		try {
			UserDAO userDAO = new UserDAO();
			User user = userDAO.validUserName(employer.getUser().getEmailId());
			if (user != null) {
				// result.rejectValue("jobSeeker.user.emailId", "error");
				result.rejectValue("user.emailId", "error.invalid.jobSeeker.user.emailId",
						"Email ID already registered!");
				mav.setViewName("employersignup");
				return mav;
			}
			//
			EmployerDAO employerDAO = new EmployerDAO();
			Employer newEmployer = employerDAO.create(employer);
		} catch (Exception e) {
			System.out.println("Exception IN sIGNUP: " + e.getMessage());
		}
		mav.addObject("check", "employer");
		mav.setViewName("signin");
		return mav;
	}
}
