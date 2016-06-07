package com.job.portal.dao;

import com.job.portal.pojo.JobapplicationStatusCode;

public class JobapplicationStatusCodeDAO extends DAO{

	public JobapplicationStatusCodeDAO(){
		
	}
	
	public JobapplicationStatusCode appliedJob(){
		begin();
		JobapplicationStatusCode jasc = (JobapplicationStatusCode)getSession().get(JobapplicationStatusCode.class, 1);
		close();
		return jasc;
	}
	
	
	
	
}
