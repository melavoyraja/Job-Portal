package com.job.portal.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import com.job.portal.pojo.Appliedjobs;

public class ApplyJobsValidator implements Validator{

	public ApplyJobsValidator() {
		// TODO Auto-generated constructor stub
//		System.out.println("******* Indise jobs validator");
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Appliedjobs.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		Appliedjobs appliedJobs = (Appliedjobs) object;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "legallyAuthorized", "error.invalid.legallyAuthorized","Required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "resumeFile", "error.invalid.resumeFile","Required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sponsorship", "error.invalid.sponsorship","Required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "citizen", "error.invalid.citizen","Required!");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "availableFrom", "error.invalid.availableFrom","Required!");
		
		MultipartFile file = appliedJobs.getResumeFile();
		if(!file.getOriginalFilename().endsWith("pdf")){
			errors.rejectValue("resumeFile","error.invalid.resumeFile","Invalid File Format");
		}
       
        System.out.println(file.getSize());
        if(1048576 < file.getSize()) {
             errors.rejectValue("resumeFile","error.invalid.resumeFile","File size is over 1MB !");
        }
		
	}
	

	
}


