package com.job.portal.misc;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class SimpleEMail {

	public void sendMail(String toAddress, String mailMessage) throws EmailException {
		Email email = configEmail();
		email.setSubject("TestMail");
		email.setMsg("This is a test mail ... :-)");
		email.addTo("melavoyraja.p@husky.neu.edu");
		email.send();
	}

	public void forgotPasswordEmail(String toAddress, String password) throws EmailException {
		Email email = configEmail();
		email.setSubject("Forgot Password Job Portal");
		String message = "Your new password is:" + password;
		email.setMsg(message);
		email.addTo("melavoyraja.p@husky.neu.edu");
		email.send();
	}

	public Email configEmail() throws EmailException {
		Email email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setAuthenticator(new DefaultAuthenticator("puneethmr1@gmail.com", "mrsreddy270889"));
		email.setSSLOnConnect(true);
		email.setFrom("puneethmr1@gmail.com");
		return email;
	}

}
