package com.job.portal.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.job.portal.pojo.Location;


public class LocationDAO extends DAO{

	public LocationDAO(){
		
	}
	
	public Location getLocation(String stateName, String cityName)throws Exception{
		try{
		begin();
		Query q = getSession().createQuery("from Location where city = :cityName and stateName = :stateName");
		q.setString("cityName", cityName);
		q.setString("stateName", stateName);
		Location location = (Location) q.uniqueResult();
		if(location == null){
			location = new Location();
			location.setCity(cityName);
			location.setStateName(stateName);
			getSession().save(location);
			commit();
		}
		return location;
		}catch(HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new Exception("Exception while creating role: " + e.getMessage());
		}finally {
			close();
		}
		
		
		
	}
	
	
}
