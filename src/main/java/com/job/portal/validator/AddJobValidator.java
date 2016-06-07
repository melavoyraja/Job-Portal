package com.job.portal.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.job.portal.pojo.Job;

public class AddJobValidator implements Validator{

	public AddJobValidator() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean supports(Class job) {
		// TODO Auto-generated method stub
		System.out.println(job.equals(Job.class));
		return job.equals(Job.class);
	}
	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		Job job = (Job) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobTitle", "error.invalid.jobTitle","Job Title Required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location.city", "error.invalid.location.city","City Name Required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "location.stateName", "error.invalid.location.stateName","State Name Required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobDescription", "error.invalid.jobDescription","Job Description Required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "desiredSkills", "error.invalid.desiredSkills","Desired Skills Required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobtype.name", "error.invalid.jobtype.name","Job Type Required!");
		
		if(job.getLocation().getStateName().length() != 2){
			errors.rejectValue("location.city", "error.invalid.location.city","City Name Required!");
		}
		
		
		
		
	}
}
