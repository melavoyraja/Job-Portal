package com.job.portal.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.job.portal.pojo.Adminapprovalstatuscode;
import com.job.portal.pojo.Employer;
import com.job.portal.pojo.Roles;

public class EmployerDAO extends DAO {
	public EmployerDAO() {

	}

	public Employer create(Employer employer) throws Exception {
		try {

			RolesDAO rolesDAO = new RolesDAO();
			AdminApprovalDAO adminApprovalDAO = new AdminApprovalDAO();
			Roles role = rolesDAO.getRole("Employer");
			Adminapprovalstatuscode statusCode = adminApprovalDAO.getStatus("Pending");
			employer.setAdminapprovalstatuscode(statusCode);
			employer.getUser().setRoles(role);
			begin();
			getSession().save(employer);
			commit();
			return employer;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while creating employer: " + e.getMessage());
		} finally {
			close();
		}
	}

	public List<Employer> displayEmployerList() {
		begin();
		Query q = getSession().createQuery("from Employer");
		// Query q = getSession().createQuery("from Employer as e where
		// e.adminapprovalstatuscode.status = :status");
		// q.setString("status", "pending");
		List<Employer> list = q.list();
		close();
		return list;

	}

	public int appproveOrRejectEmployer(int employerId, int statusId) throws Exception {
		try {
			begin();
			Query q = getSession()
					.createQuery("update Employer set statusId = :statusId where employerId = :employerId");
			q.setInteger("statusId", statusId);
			q.setInteger("employerId", employerId);
			int out = q.executeUpdate();
			commit();
			return out;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Exception while approving employer: " + e.getMessage());
		} finally {
			close();
		}

	}

}
