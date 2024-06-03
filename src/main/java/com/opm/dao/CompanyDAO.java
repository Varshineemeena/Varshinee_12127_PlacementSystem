package com.opm.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.opm.entity.Company;
import com.opm.entity.Student;

public interface CompanyDAO {
	
	public void addCompany(Company company);

	 List<Company> getallCompanys();
	
//	 List<Company> getCompanyById(int id);
	 
	 Optional<Company> getCompanyByEmailId(String email);
	 
	 List<Long> getCompanyIdlist();
	 
	 //Approve
	 Company getCompanyId(Long id);
	 
	 ResponseEntity<?> updateBill(long id,Company updatedCompany);
	 
	    void deleteCompany(Long id);


}
