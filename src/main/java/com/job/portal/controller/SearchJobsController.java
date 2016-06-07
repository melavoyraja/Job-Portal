package com.job.portal.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.job.portal.dao.JobDAO;
import com.job.portal.pojo.Job;

@Controller
@RequestMapping("/search.htm")
public class SearchJobsController {

	public SearchJobsController(){
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView searchJobs(@RequestParam("jobTitle") String jobTitle,@RequestParam("pageNo") int pageNo){
		ModelAndView mav = new ModelAndView();
		JobDAO jobDAO = new JobDAO();
		List<Job> jobList = jobDAO.searchJobs(jobTitle,(pageNo*10));
		long numberOfResults = jobDAO.pageCount(jobTitle);
//		System.out.println("***** number of jobs"+numberOfResults);
		mav.setViewName("search");
		mav.addObject("jobList", jobList);
		mav.addObject("numberOfResults", numberOfResults);
		
		return mav;
	}
	
}
