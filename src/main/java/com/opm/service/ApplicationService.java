package com.opm.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.opm.entity.Applications;

public interface ApplicationService {
	public void addApplication(Applications applications);

	 List<Applications> getallApplications();
	 
//	 List<Applications> getApplicationsById(Long id);
	 
	 List<Applications> getByStudentId(Long id);

	 List<Applications> getByCompanyId(Long id);
	 
	 //Approve
	 Applications getApplicationId(Long id);
	 
	 ResponseEntity<?> updateBill(long id,Applications updatedApplication);
	 ResponseEntity<?> updateInterview(long id,Applications updatedInterview);
	 
	 void deleteApplication(Long id);


}
