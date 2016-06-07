package com.job.portal.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.job.portal.pojo.Adminapprovalstatuscode;

public class AdminapprovalstatuscodeDAO extends DAO{

	public AdminapprovalstatuscodeDAO(){
		
	}
	
	public Adminapprovalstatuscode getRole(String status) throws Exception{
		try{
		begin();
		
		Query q = getSession().createQuery("from Adminapprovalstatuscode where status = :status");
		q.setString("status", status);
		q.setMaxResults(1);
		Adminapprovalstatuscode adminapprovalstatuscode = (Adminapprovalstatuscode) q.uniqueResult();
		if(adminapprovalstatuscode == null){
			adminapprovalstatuscode = new Adminapprovalstatuscode();
			adminapprovalstatuscode.setStatus(status);
			getSession().save(adminapprovalstatuscode);
			commit();
		}
		return adminapprovalstatuscode;
		}catch(HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new Exception("Exception while creating admin approval status code: " + e.getMessage());
		}finally {
			close();
		}
	}
	
	
}
