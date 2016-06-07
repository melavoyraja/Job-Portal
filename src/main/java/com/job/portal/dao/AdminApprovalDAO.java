package com.job.portal.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.job.portal.pojo.Adminapprovalstatuscode;

public class AdminApprovalDAO extends DAO{

	public AdminApprovalDAO(){
		
	}
	
	public Adminapprovalstatuscode getStatus(String statusName) throws Exception{
		try{
		begin();
		Query q = getSession().createQuery("from Adminapprovalstatuscode where status = :statusName");
		q.setString("statusName", statusName);
		Adminapprovalstatuscode statusCode = (Adminapprovalstatuscode) q.uniqueResult();
		if(statusCode == null){
			statusCode = new Adminapprovalstatuscode();
			statusCode.setStatus(statusName);
			getSession().save(statusCode);
			commit();
		}
		return statusCode;
		}catch(HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new Exception("Exception while creating role: " + e.getMessage());
        }finally {
			close();
		}
	}
	
}
