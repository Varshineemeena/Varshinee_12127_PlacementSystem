package com.opm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.opm.dao.JobDAO;
import com.opm.entity.Job;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class JobServiceImpl implements JobService {
	
	@Autowired
	JobDAO dao;

	@Override
	public void addJob(Job job) {
		dao.addJob(job);
		
	}

	@Override
	public List<Job> getallJobs() {
		
		return dao.getallJobs() ;
	}

	@Override
	public List<Long> getJobIdlist() {
		
		return dao.getJobIdlist();
	}

	@Override
	public List<Job> getByCompanyIdss(Long id) {
		
		return dao.getByCompanyIdss(id);
	}

	@Override
	public Job getJobId(Long id) {
		
		return dao.getJobId(id);
	}

	@Override
	public ResponseEntity<?> updateBill(long id, Job updatedJob) {
		
		return dao.updateBill(id, updatedJob);
	}

	@Override
	public void deleteJob(Long id) {
		dao.deleteJob(id);
		
	}

}
