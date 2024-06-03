package com.opm.testing;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

import com.opm.controller.JobController;
import com.opm.entity.Company;
import com.opm.entity.Job;
import com.opm.entity.Student;

@SpringBootTest
class JobTesting {
	
	@Autowired
	JobController jobcontroller;
	
	@Test
	void performAddJobTestWithDifferentInput() {
	    Job job=new Job();
	    job.setRolename("Junior software engineer");
	    job.setQualification("B.E");
	    job.setJobStatus("Open");
	    Company company = new Company();
	    company.setCompanyId(6L);
	    job.setCompany(company);
	    
	    String insert="Job Details Saved";
	    String result=jobcontroller.addJob(job);
	    Assert.assertEquals(insert, result);
	}
	
	@Test
	void performGetAllJob() {
		Long jobId = 6L;
		List<Job> jobList = jobcontroller.getAllJob();
		assertNotNull(jobList);
	}


	@Test
	void performGetJobById() {
		Long jobId = 6L;
		List<Long> companyList = jobcontroller.getJobIdlists();
		assertNotNull(companyList);
	}
	
	@Test
	void performGetJobmById() {
		Long jobId = 6L;
		List<Job> jobidlist = jobcontroller.getByJobId(jobId);
		assertNotNull(jobidlist);
	
	
	
}
}