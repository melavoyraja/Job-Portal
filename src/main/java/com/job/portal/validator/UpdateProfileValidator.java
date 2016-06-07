package com.job.portal.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.job.portal.pojo.JobSeeker;

public class UpdateProfileValidator implements Validator{

	public UpdateProfileValidator() {
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
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "yearsExperience", "error.invalid.yearsExperience","Years of Experience Required!");
		
		if(!(jobSeeker.getUser().getFirstName().trim().length() >= 2 && jobSeeker.getUser().getFirstName().trim().length() <= 100)){
			errors.rejectValue("user.firstName","Length","First Name must be between 2 and 100 characters long");
		}
		
		if(!(jobSeeker.getUser().getLastName().trim().length() >= 2 && jobSeeker.getUser().getLastName().trim().length() <= 100)){
			errors.rejectValue("user.lastName","Length","First Name must be between 2 and 100 characters long");
		}
		
		
		
	}

}
