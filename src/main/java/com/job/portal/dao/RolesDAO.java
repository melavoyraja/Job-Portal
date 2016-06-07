package com.job.portal.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.job.portal.pojo.Roles;

public class RolesDAO extends DAO{

	public RolesDAO(){
		
	}
	
	
	public Roles getRole(String roleName) throws Exception{
		try{
		begin();
		Query q = getSession().createQuery("from Roles where role = :role");
		q.setString("role", roleName);
		q.setMaxResults(1);
		Roles roles = (Roles) q.uniqueResult();
		if(roles == null){
			roles = new Roles();
			roles.setRole(roleName);
			getSession().save(roles);
			commit();
		}
		return roles;
		}catch(HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new Exception("Exception while creating role: " + e.getMessage());
		}finally {
			close();
		}
	}
}
