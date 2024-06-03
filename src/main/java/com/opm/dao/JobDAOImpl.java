package com.opm.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

import com.opm.entity.Applications;
import com.opm.entity.Company;
import com.opm.entity.Job;
import com.opm.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class JobDAOImpl implements JobDAO {
	
	private EntityManager eman;

	public JobDAOImpl() {
		super();
		
	}
	
	@Autowired
	public JobDAOImpl(EntityManager entity) {
		super();
		this.eman=entity;
	}
	
	@Override
    public void addJob(Job job) {
        try {
            eman.persist(job);
           
        }catch(Exception e) {
            e.printStackTrace();
        }
	}
	@Override
	public List<Long> getJobIdlist() {
		Query q = eman.createQuery("SELECT v.id FROM Job v");
		List<Long> list = q.getResultList();
		return list;
	}

	@Override
    public List<Job> getallJobs() {
        List<Job> job = new ArrayList<>();
        try {
            job = eman.createQuery("from Job E").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return job;
    }
	

    
    public void updateJobInfo(Job job) {
        try {
            eman.createQuery
            ("UPDATE Job E SET E.rolename= :rolename, E.description= :description, E.jobStatus = :jobStatus, E.jobQualification = :jobQualification, E.company = :company WHERE E.jobId= :jobId")
            .setParameter("jobId", job.getJobId())
            .setParameter("rolename", job.getRolename())
            .setParameter("jobStatus", job.getJobStatus()).setParameter("jobQualification", job.getQualification()).setParameter("company", job.getCompany())
            .executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    @Override
	public List<Job> getByCompanyIdss(Long id) {
		try {
			return eman.createQuery("FROM Job where company.companyId =:companyId", Job.class)
			.setParameter("companyId", id).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			
		}
	}
    

    
    
// Approve
    
    
	@Override
	public ResponseEntity<?> updateBill(long jobId, Job updatedJob) {
		Job existingJob = eman.find(Job.class, jobId);
				
        if (existingJob == null) {
            return ResponseEntity.badRequest().body("Job not found");
        }
        
        // Update
        existingJob.setJobStatus(updatedJob.getJobStatus());
//        existingStudent.setAdmin(updatedStudent.getAdmin());

        // Save the update
        eman.persist(existingJob);
        return ResponseEntity.ok("Details updated successfully");
	}

	@Override
	public Job getJobId(Long id) {
		return eman.find(Job.class, id);
		
	}
	
	@Override
	public void deleteJob(Long id) {
	     Job jobById = getJobId(id);
	     eman.remove(jobById);
	 }
	public Job getJobById(Long id) {
		return eman.find(Job.class, id);
	}
	
	

}
