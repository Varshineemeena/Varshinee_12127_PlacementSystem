package com.opm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "applications")
public class Applications {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationId;
    private String applicationStatus;
    private String interviewStatus;
    
    @ManyToOne
    @JoinColumn(name = "studentId")
    @JsonIgnoreProperties("applications")
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "jobId")
    @JsonIgnoreProperties("applications")
    private Job job;
    
    @ManyToOne
    @JoinColumn(name = "adminId")
    @JsonIgnoreProperties("applications")
    private Admin admin;

	public Applications(Long applicationId, String applicationStatus, String interviewStatus, Student student, Job job,
			Admin admin) {
		super();
		this.applicationId = applicationId;
		this.applicationStatus = applicationStatus;
		this.interviewStatus = interviewStatus;
		this.student = student;
		this.job = job;
		this.admin = admin;
	}

	public Applications() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	
	
    
    
 
}
