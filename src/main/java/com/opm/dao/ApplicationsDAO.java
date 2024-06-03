package com.opm.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.opm.entity.Applications;
import com.opm.entity.Company;
import com.opm.entity.Job;
import com.opm.entity.Student;

public interface ApplicationsDAO {
	
	public void addApplication(Applications applications);

	 List<Applications> getallApplications();
	 
//	 List<Applications> getApplicationsById(Long id);
	 
	 List<Applications> getByStudentId(Long id);

	 List<Applications> getByCompanyId(Long id);
	 
	 //Approve
	 Applications getApplicationId(Long id);
	 
	 ResponseEntity<?> updateBill(long id,Applications updatedApplication);
	 
	 ResponseEntity<?> updateInterview(long id,Applications updatedApplication);
	 
	 
	
		    void deleteApplication(Long id);
		


}
