package com.job.portal.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.job.portal.pojo.User;

public class SignInUserValidator implements Validator{

	public SignInUserValidator() {
		
	}
	
	@Override
	public boolean supports(Class user) {
		// TODO Auto-generated method stub
		return user.equals(User.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailId", "error.invalid.emailId","Username Required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password","Password Required!");
		
	}

}
