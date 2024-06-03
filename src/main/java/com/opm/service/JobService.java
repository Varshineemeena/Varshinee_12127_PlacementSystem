package com.opm.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.opm.entity.Job;

public interface JobService {
	
	public void addJob(Job job);

	 List<Job> getallJobs();
	 
//	 List<Job> getJobById(Long id);

	 List<Long> getJobIdlist();
	 
	 List<Job> getByCompanyIdss(Long id);
	 

	//Approve
	 
		 Job getJobId(Long id);
		 
		 ResponseEntity<?> updateBill(long id,Job updatedJob);
		 
		    void deleteJob(Long id);

	 

}
