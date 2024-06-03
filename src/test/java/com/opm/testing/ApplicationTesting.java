package com.opm.testing;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

import com.opm.controller.ApplicationController;
import com.opm.entity.Admin;
import com.opm.entity.Applications;
import com.opm.entity.Company;
import com.opm.entity.Job;
import com.opm.entity.Student;

@SpringBootTest
class ApplicationTesting {
	
	@Autowired
	ApplicationController applicationcontroller;
	
	@Test
	void performAddApplicationTest() {
	    Applications application=new Applications();
	    application.setApplicationStatus("Pending");
	    application.setInterviewStatus("Pending");
	    
	    Admin admin = new Admin();
	    admin.setAdminId(1L);
	    application.setAdmin(admin);
	    
	    Job job = new Job();
	    job.setJobId(15L);
	    application.setJob(job);
	    
	    Student student = new Student();
	    student.setStudentId(1L);
	    application.setStudent(student);
	    
	    
	    String insert="Application Details Saved";
	    String result=applicationcontroller.addApplication(application);
	    Assert.assertEquals(insert, result);
	}
	
	@Test
	void performGetAllApplication() {
		Long applicationId = 6L;
		List<Applications> applicationList = applicationcontroller.getallApplications();
		assertNotNull(applicationList);
	}


	
	@Test
	void performGetApplicationmById() {
		Long applicationId = 6L;
		List<Applications> applicationList = applicationcontroller.getByStudentId(1l);
		assertNotNull(applicationList);
	
	
	
}

	

}
