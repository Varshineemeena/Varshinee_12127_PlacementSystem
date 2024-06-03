package com.opm.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;
    private String rolename;
    private String qualification;
    private String jobStatus;
       
    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("Job")
	private List<Applications> applications;
    
    @ManyToOne
    @JoinColumn(name = "companyId")
    @JsonIgnoreProperties("job") 
    private Company company;

	public Job(Long jobId, String rolename, String qualification, String jobStatus, List<Applications> applications,
			Company company) {
		super();
		this.jobId = jobId;
		this.rolename = rolename;
		this.qualification = qualification;
		this.jobStatus = jobStatus;
		this.applications = applications;
		this.company = company;
	}

	public Job() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public List<Applications> getApplications() {
		return applications;
	}

	public void setApplications(List<Applications> applications) {
		this.applications = applications;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
    

	
	

	

	
    
    

}
