package com.job.portal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.job.portal.dao.JobSeekerDAO;
import com.job.portal.misc.RetriveLoggedInUser;
import com.job.portal.pojo.User;

@Controller
@RequestMapping("/jobseeker/withdrawjobapplication.htm")
public class WithdrawJobApplication {

	public WithdrawJobApplication() {

	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView withdrawApplication(@RequestParam("id") String jobId, HttpServletRequest request)
			throws Exception {
		ModelAndView mav = new ModelAndView();
		try {
			User user = (User) request.getSession().getAttribute("hibuser");
			if (user == null) {
				RetriveLoggedInUser retriveUserName = new RetriveLoggedInUser();
				User hibuser = retriveUserName.getPrincipal();
				user = hibuser;
				request.getSession().setAttribute("hibuser", hibuser);
			}
			JobSeekerDAO jobSeekerDAO = new JobSeekerDAO();
			boolean check = jobSeekerDAO.withdrawJobApplication(user.getJobseeker(), jobId);
			mav.setViewName("forward:jobseekerdashboard.htm");
			mav.addObject("check", check);
			return mav;
		} catch (NumberFormatException e) {
			mav.setViewName("forward:jobseekerdashboard.htm");
			mav.addObject("check", false);
			return mav;
		}
	}

}
