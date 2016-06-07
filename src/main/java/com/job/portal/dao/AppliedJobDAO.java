package com.job.portal.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.job.portal.pojo.Appliedjobs;
import com.job.portal.pojo.JobapplicationStatusCode;
import com.job.portal.pojo.User;

public class AppliedJobDAO extends DAO {

	public AppliedJobDAO() {

	}

	public void applyJob(Appliedjobs applyJob) throws Exception {
		try {

			// JobDAO jobDAO = new JobDAO();
			applyJob.setAppliedDate(new Date());
			JobapplicationStatusCodeDAO jasc = new JobapplicationStatusCodeDAO();
			JobapplicationStatusCode appliedStatusCode = jasc.appliedJob();
			// System.out.println(appliedStatusCode.getStatus());
			begin();
			// getSession().persist(appliedStatusCode);
			applyJob.setJobApplicationStatusCode(appliedStatusCode);
			// applyJob.setJob(jobDAO.getJob(String.valueOf(applyJob.getJobId())));

			getSession().save(applyJob);

			commit();

		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while applying job: " + e.getMessage());
		} finally {
			close();
		}
	}

	public List<Appliedjobs> getAppliedJobsListByJobSeekerID(Integer jobSeekerId) {
		begin();
		Query q = getSession().createQuery("from Appliedjobs a where jobSeekerId = :jobSeekerId ORDER BY a.jobId ASC");
		q.setInteger("jobSeekerId", jobSeekerId);
		List<Appliedjobs> list = q.list();
		close();
		return list;
		// begin();
		//// getSession().persist(user);
		//// getSession().saveOrUpdate(user.getJobseeker());
		// commit();

		// return user;
	}

	public List<Appliedjobs> getAppliedJobsListByJobID(Integer jobID) {
		begin();
		Query q = getSession().createQuery("from Appliedjobs where jobId =:jobId");
		q.setInteger("jobId", jobID);
		List<Appliedjobs> list = q.list();
		close();
		return list;
	}

	public Appliedjobs getAppliedJob(String id) {
		begin();
		Appliedjobs appliedJob = getSession().get(Appliedjobs.class, Integer.parseInt(id));
		close();
		return appliedJob;

	}

	public boolean checkAlreayApplied(int jobSeekerId, int jobId) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from Appliedjobs where jobId =:jobId and jobSeekerId = :jobSeekerId");
			q.setInteger("jobSeekerId", jobSeekerId);
			q.setInteger("jobId", jobId);
			q.setMaxResults(1);
			Appliedjobs appliedJob = (Appliedjobs) q.uniqueResult();
			if (appliedJob != null) {
				return true;
			}
			return false;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while applying job: " + e.getMessage());
		} finally {
			close();
		}
	}

	public boolean updateAppliedJobStatus(int statusId, String id) {
		boolean check = false;
		begin();
		Query q = getSession()
				.createQuery("update Appliedjobs set jobApplicationStatusCode.statusId = :statusId where id = :id");
		q.setInteger("statusId", statusId);
		q.setInteger("id", Integer.parseInt(id));
		int out = q.executeUpdate();
		commit();
		if (out == 1) {
			check = true;
		}
		close();
		return check;
	}

}
