package com.job.portal.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.job.portal.pojo.Employer;

public class SignUpEmployerValidator implements Validator{

	public SignUpEmployerValidator() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean supports(Class employer) {
		// TODO Auto-generated method stub
		return employer.equals(Employer.class);
	}
	
	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		Employer employer = (Employer) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.firstName", "error.invalid.firstName","First Name Required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.lastName", "error.invalid.lastName","Last Name Required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.emailId", "error.invalid.email","Username Required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.password", "error.invalid.password","Password Required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "companyName", "error.invalid.companyName","Company Name Required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "error.invalid.phone","Phone Number Required!");

		
	}
	
}
