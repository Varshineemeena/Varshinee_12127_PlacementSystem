package com.opm.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.opm.entity.Applications;
import com.opm.entity.Company;
import com.opm.entity.Job;
import com.opm.entity.Student;

public interface JobDAO {
	public void addJob(Job job);

	 List<Job> getallJobs();
	 
//	 List<Job> getJobById(Long id);

	 List<Long> getJobIdlist();
	 
	 List<Job> getByCompanyIdss(Long id);
	 
//	 List<Job> getByStudentIdss(Long id);
	 

	//Approve
	 
		 Job getJobId(Long id);
		 
		 ResponseEntity<?> updateBill(long id,Job updatedJob);
		 
		    void deleteJob(Long id);

	 
	

}
