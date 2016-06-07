package com.job.portal.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.job.portal.pojo.JobSeeker;


public class SignUpJobSeekerValidator implements Validator{
	
	public SignUpJobSeekerValidator() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean supports(Class jobSeeker) {
		// TODO Auto-generated method stub
		return jobSeeker.equals(JobSeeker.class);
	}

	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		JobSeeker jobSeeker = (JobSeeker) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.firstName", "error.invalid.firstName","First Name Required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.lastName", "error.invalid.lastName","Last Name Required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.emailId", "error.invalid.email","Username Required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.password", "error.invalid.password","Password Required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "yearsExperience", "error.invalid.yearsExperience","Years of Experience Required!");
		
	}

}
