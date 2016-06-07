package com.job.portal.dao;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;

import com.job.portal.pojo.User;

public class UserAuthenticationDAO implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDAO userDAO = new UserDAO();
		User user = userDAO.validUserName(email);
		if(user == null){
			System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
		}
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRoles().getRole()));
		System.out.println(user.getRoles().getRole());
		 return new org.springframework.security.core.userdetails.User(user.getEmailId(), user.getPassword(), 
                 true, true, true, true, authorities);
		 
	}
	
}
