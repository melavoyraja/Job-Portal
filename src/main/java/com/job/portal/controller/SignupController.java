package com.job.portal.controller;

import javax.validation.Valid;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.job.portal.dao.JobSeekerDAO;
import com.job.portal.dao.UserDAO;
import com.job.portal.misc.SimpleEMail;
import com.job.portal.pojo.JobSeeker;
import com.job.portal.pojo.User;
import com.job.portal.validator.SignUpJobSeekerValidator;

@Controller
@RequestMapping("/signup.htm")
public class SignupController {

	// SignUpJobSeekerValidator suuav;
	//
	// @Autowired
	// public SignupController(SignUpJobSeekerValidator suuav) {
	// // TODO Auto-generated constructor stub
	// this.suuav = suuav;
	// }

	// @InitBinder
	// private void initBinder(WebDataBinder dataBinder) {
	// dataBinder.setValidator(suuav);
	// }

	@RequestMapping(method = RequestMethod.GET)
	protected String initializeForm(@ModelAttribute("jobSeeker") @Valid JobSeeker jobSeeker, BindingResult result)
			throws EmailException {
		//
		// JobSeekerDAO jobSeekerDAO = new JobSeekerDAO();
		// JobSeeker jobSeeker1 = jobSeekerDAO.getSession().get(JobSeeker.class,
		// 1);
		// ModelAndView mav = new ModelAndView();
		// mav.addObject("jobSeeker1", jobSeeker1);
		// mav.setViewName("signup");
		// return mav;
		// SimpleEMail mail = new SimpleEMail();
		// mail.sendMail();
		return "signup";
	}

	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doSubmitAction(@ModelAttribute("jobSeeker") @Valid JobSeeker jobSeeker, BindingResult result)
			throws Exception {
		// suuav.validate(jobSeeker, result);
		ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
			mav.setViewName("signup");
			return mav;
		}
		UserDAO userDAO = new UserDAO();
		User user = userDAO.validUserName(jobSeeker.getUser().getEmailId());
		if (user != null) {
//result.rejectValue("jobSeeker.user.emailId", "error");
			result.rejectValue("user.emailId", "error.invalid.jobSeeker.user.emailId", "Email ID already registered!");
			mav.setViewName("signup");
			return mav;
		}
		//
		// DAO dao = new DAO();
		// UserAccountDAO userAccountDAO = new UserAccountDAO();
		// userAccountDAO.create(userAccount.getEmail(),
		// userAccount.getPassword());
		JobSeekerDAO jobSeekerDAO = new JobSeekerDAO();
		JobSeeker js = jobSeekerDAO.create(jobSeeker);

		mav.addObject("check", "jobseeker");
		mav.setViewName("signin");
		return mav;
	}

}
