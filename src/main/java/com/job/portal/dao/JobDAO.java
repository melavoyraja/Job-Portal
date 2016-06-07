package com.job.portal.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.job.portal.pojo.Appliedjobs;
import com.job.portal.pojo.Employer;
import com.job.portal.pojo.Job;
import com.job.portal.pojo.User;
import com.mysql.jdbc.DatabaseMetaDataUsingInfoSchema;

public class JobDAO extends DAO {

	public JobDAO() {

	}

	public Job createJob(Job job) throws Exception {
		try {

			// SimpleDateFormat date = new SimpleDateFormat("yyyy/MM/dd");
			Date date = new Date();
			Date newDate = addDays(date, 60);
			// job.setDatePosted(date.format(new Date()));
			job.setDatePosted(date);
			job.setDeadlinToApply(newDate);
			JobTypeDAO jobTypeDAO = new JobTypeDAO();
			job.setJobtype(jobTypeDAO.getJobType(job.getJobtype().getName()));
			System.out.println(job.getDeadlinToApply().toString());
			System.out.println(date.toString());
			begin();
			getSession().save(job);
			commit();

			return job;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating create job: " + e.getMessage());
		} finally {
			close();
		}
	}

	public List<Job> getLatestFiveJobs() throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from Job order by jobId desc");
			q.setMaxResults(5);
			List<Job> jobs = q.list();
			return jobs;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while pulling latest 5 jobs: " + e.getMessage());
		} finally {
			close();
		}
	}

	public List<Job> searchJobs(String jobTitle, int startRow) {
		begin();
		Query q = getSession().createQuery("from Job where jobTitle like :jobTitle");
		q.setFirstResult(startRow);
		q.setMaxResults(10);
		q.setString("jobTitle", '%' + jobTitle + '%');
		List<Job> jobList = q.list();
		close();
		return jobList;
	}

	public Long pageCount(String jobTitle) {
		begin();
		Query q = getSession().createQuery("select count(*) from Job where jobTitle like :jobTitle");
		q.setString("jobTitle", '%' + jobTitle + '%');
		q.setMaxResults(1);
		Long count = (Long) q.uniqueResult();
		close();
		return count;
	}

	public Job getJob(String jobId) {
		begin();
		Job job = (Job) getSession().get(Job.class, Integer.parseInt(jobId));
		close();
		return job;
	}

	public Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); // minus number would decrement the days
		return cal.getTime();
	}

	public boolean checkIfAlreadyApplied(String jobId, int userId) {
		boolean check = false;
		begin();
		Query q = getSession().createQuery("from User where userId = :userId");
		q.setInteger("userId", userId);
		q.setMaxResults(1);
		User u = (User) q.uniqueResult();
		for (Appliedjobs appliedJob : u.getJobseeker().getAppliedjobses()) {
			if (appliedJob.getJobId() == Integer.parseInt(jobId)) {
				return true;
			}
		}

		return check;
	}

}
