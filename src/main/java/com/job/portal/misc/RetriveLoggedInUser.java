package com.job.portal.misc;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.job.portal.dao.UserDAO;
import com.job.portal.pojo.User;

public class RetriveLoggedInUser {
	
	public User getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUsername();
		} else {
			userName = principal.toString();
		}
		UserDAO userDAO = new UserDAO();
		User hibuser = userDAO.validUserName(userName);
		
		return hibuser;
	}
}
