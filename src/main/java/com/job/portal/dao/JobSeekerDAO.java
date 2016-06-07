package com.job.portal.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.job.portal.pojo.Appliedjobs;
import com.job.portal.pojo.JobSeeker;
import com.job.portal.pojo.Roles;

public class JobSeekerDAO extends DAO {

	public JobSeekerDAO() {
		System.out.println("******** JobSeeker DAO");
	}

	public JobSeeker create(JobSeeker jobSeeker) throws Exception {
		try {

			RolesDAO rolesDAO = new RolesDAO();
			Roles role = rolesDAO.getRole("JobSeeker");
			jobSeeker.getUser().setRoles(role);
			begin();
			getSession().save(jobSeeker);
			commit();
			return jobSeeker;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating job seeker: " + e.getMessage());
		} finally {
			close();
		}

	}

	public boolean validate(String emailId, String password) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from UserAccount where email = :username and password = :password");
			q.setString("username", emailId);
			q.setString("password", password);
			JobSeeker js = (JobSeeker) q.uniqueResult();
			if (js != null) {
				if (js.getUser().getEmailId().equals(emailId) && js.getUser().getPassword().equals(password)) {
					return true;
				}

			}

			return false;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating job seeker: " + e.getMessage());
		} finally {
			close();
		}

	}

	public boolean withdrawJobApplication(JobSeeker jobSeeker, String appliedjobId) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from Appliedjobs where jobSeekerId = :jobSeekerId");
			q.setInteger("jobSeekerId", jobSeeker.getJobSeekerId());
			List<Appliedjobs> appliedJobs = q.list();

			for (Appliedjobs appliedJob : appliedJobs) {
				if (Integer.parseInt(appliedjobId) == appliedJob.getId()) {
					begin();
					Appliedjobs retrieveappliedJob = (Appliedjobs) getSession().get(Appliedjobs.class,
							Integer.parseInt(appliedjobId));
					getSession().delete(retrieveappliedJob);
					commit();
					close();
					return true;
				}
			}

			return false;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating job seeker: " + e.getMessage());
		} finally {
			close();
		}

	}

	public boolean updateProfile(JobSeeker jobSeeker) {
		boolean check = false;
		begin();
		Query q = getSession().createQuery(
				"update JobSeeker set user.firstName = :firstName,user.lastName= :lastName,yearsExperience = :yearsExperience where jobSeekerId = :jobSeekerId ");
		q.setString("firstName", jobSeeker.getUser().getFirstName());
		q.setString("lastName", jobSeeker.getUser().getLastName());
		q.setInteger("yearsExperience", jobSeeker.getYearsExperience());
		int update = q.executeUpdate();
		if (update >= 1) {
			check = true;
		}
		return check;
	}
}
