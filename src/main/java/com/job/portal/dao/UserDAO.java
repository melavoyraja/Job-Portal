package com.job.portal.dao;

import java.math.BigInteger;
import java.security.SecureRandom;

import org.apache.commons.mail.EmailException;
import org.hibernate.Query;

import com.job.portal.misc.SimpleEMail;
import com.job.portal.pojo.User;

public class UserDAO extends DAO {

	public UserDAO() {

	}

	public User validate(User u) {
		begin();
		Query q = getSession().createQuery("from User where emailId = :username and password = :password");
		q.setString("username", u.getEmailId());
		q.setString("password", u.getPassword());
		q.setMaxResults(1);
		User user = (User) q.uniqueResult();
		close();
		return user;
	}

	public User getUser(int id) {
		begin();
		Query q = getSession().createQuery("from User where id = :id");
		q.setString("id", String.valueOf(id));
		User user = (User) q.uniqueResult();
		close();
		return user;
	}

	public User validEmailAddress(User u) {
		begin();
		Query q = getSession().createQuery("from User where emailId = :username");
		q.setString("username", u.getEmailId());
		q.setMaxResults(1);
		User user = (User) q.uniqueResult();
		close();
		return user;
	}
	
	public User validUserName(String userName) {
		begin();
		Query q = getSession().createQuery("from User where emailId = :username");
		q.setString("username", userName);
		q.setMaxResults(1);
		User user = (User) q.uniqueResult();
		close();
		return user;
	}

	public boolean generateNewPassword(User u) throws EmailException {
		boolean check = false;
		SecureRandom random = new SecureRandom();
		String newPassword = new BigInteger(30, random).toString(16);
		System.out.println(newPassword);
		begin();
		Query q = getSession().createQuery("update User u set u.password = :password where u.emailId = :username");
		q.setString("username", u.getEmailId());
		q.setString("password", newPassword);
		int out = q.executeUpdate();
		commit();
		if (out == 1) {
			check = true;
		}
		close();
		SimpleEMail email = new SimpleEMail();
		email.forgotPasswordEmail(u.getEmailId(), newPassword);
		return check;
	}

}
