package com.job.portal.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/signout.htm")
public class SignOutController {

	@RequestMapping(method = RequestMethod.GET)
	protected String signout(HttpServletRequest request, HttpServletResponse response) {
//		Cookie[] cookies = request.getCookies();
		Cookie c = new Cookie("my-remember-me", "");
		c.setMaxAge(0);
		response.addCookie(c);
//		for (int i = 0; i < cookies.length; i++) {
//			cookies[i].setMaxAge(0);
//		}
		request.getSession().invalidate();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "signout";
	}
}
