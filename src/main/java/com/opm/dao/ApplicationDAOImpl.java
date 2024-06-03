package com.opm.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.opm.entity.Applications;
import com.opm.entity.Company;
import com.opm.entity.Job;
import com.opm.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class ApplicationDAOImpl implements ApplicationsDAO {
	private EntityManager eman;

	public ApplicationDAOImpl() {
		super();
		
	}
	
	@Autowired
	public ApplicationDAOImpl(EntityManager entity) {
		super();
		this.eman=entity;
	}
	
	@Override
    public void addApplication(Applications applications) {
        try {
            eman.persist(applications);
           
        }catch(Exception e) {
            e.printStackTrace();
        }
	}

//	@Override
//    public List<Applications> getallApplications() {
//        List<Applications> applications=null;
//        try {
//            applications = eman.createNativeQuery("select * from applications").getResultList();
//           for(Applications s:applications)
//        	   	System.out.println(s);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return applications;
//    }
	
	@Override
    public List<Applications> getallApplications() {
        List<Applications> applications = new ArrayList<>();
        try {
        	applications = eman.createQuery("from Applications E").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return applications;
    }
	

    
    
    public void updateApplicationsInfo(Applications applications) {
        try {
            eman.createQuery
            ("UPDATE Applications E SET E.applicationStatus= :applicationStatus, E.student = :student, E.job = :job WHERE E.applicationId= :applicationId")
            .setParameter("applicationId", applications.getApplicationId())
            .setParameter("applicationStatus", applications.getApplicationStatus())
            .setParameter("student", applications.getStudent()).setParameter("job", applications.getJob())
            .executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public List<Applications> getByStudentId(Long id) {
		try {
			return eman.createQuery("FROM Applications where student.studentId =:studentId", Applications.class)
			.setParameter("studentId", id).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}
	
	@Override
	public List<Applications> getByCompanyId(Long id) {
		try {
			return eman.createQuery("FROM Applications where company.companyId =:companyId", Applications.class)
			.setParameter("companyId", id).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}
	
	//-------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public ResponseEntity<?> updateBill(long applicationId, Applications updatedApplication) {
		Applications existingApplication = eman.find(Applications.class, applicationId);
				
        if (existingApplication == null) {
            return ResponseEntity.badRequest().body("Application not found");
        }
        
        // Update
        existingApplication.setApplicationStatus(updatedApplication.getApplicationStatus());
//        existingStudent.setAdmin(updatedStudent.getAdmin());

        // Save the update
        eman.persist(existingApplication);
        return ResponseEntity.ok("Details updated successfully");
	}
//-----------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public ResponseEntity<?> updateInterview(long applicationId, Applications updatedInterview) {
		Applications existingApplication = eman.find(Applications.class, applicationId);
				
        if (existingApplication == null) {
            return ResponseEntity.badRequest().body("Application not found");
        }
        
        // Update
        existingApplication.setInterviewStatus(updatedInterview.getInterviewStatus());
//        existingStudent.setAdmin(updatedStudent.getAdmin());

        // Save the update
        eman.persist(existingApplication);
        return ResponseEntity.ok("Details updated successfully");
	}
//---------------------------------------------------------------
	@Override
	public Applications getApplicationId(Long id) {
		return eman.find(Applications.class, id);
		
	}
	

	
	@Override
    public void deleteApplication(Long id) {
        Applications applicationById = getapplicationById(id);
        eman.remove(applicationById);
    }
	
	public Applications getapplicationById(Long id) {
		return eman.find(Applications.class, id);
	}

	
	

}
