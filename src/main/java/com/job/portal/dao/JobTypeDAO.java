package com.job.portal.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.job.portal.pojo.Jobtype;

public class JobTypeDAO extends DAO{
	
	public JobTypeDAO(){
		
	}
	
	public List<Jobtype> getJobTypes() throws Exception{
		try{
			begin();
			Query q = getSession().createQuery("from Jobtype");
			List<Jobtype> list = q.list();
			return list;
			
		}catch(HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new Exception("Exception while creating get jo type DAO: " + e.getMessage());
		}finally {
			close();
		}
		
	}
	
	public Jobtype getJobType(String name) throws Exception{
		try{
			begin();
			Query q = getSession().createQuery("from Jobtype where name = :name");
			q.setString("name", name);
			Jobtype jobType = (Jobtype)q.uniqueResult();
			return jobType;
			
		}catch(HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new Exception("Exception while creating get jo type DAO: " + e.getMessage());
		}finally {
			close();
		}
	}
	
	
}
