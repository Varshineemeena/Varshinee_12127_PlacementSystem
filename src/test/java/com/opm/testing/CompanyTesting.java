package com.opm.testing;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;

import com.opm.controller.CompanyController;
import com.opm.entity.Company;
import com.opm.entity.Student;

@SpringBootTest
class CompanyTesting {
	
	@Autowired
	CompanyController companycontroller;
	
	@Test
	void performComapnyRegistration() {
		Company company = new Company();
		company.setName("Cognizant Services");
		company.setAddress("ELCOT");
		company.setEmail("cts@gmail.com");
		company.setPassword("Cts@12345");
		company.setUrl("www.cts.com");
		company.setStatus("Pending");
		
		
		String insert = "Company registered successfully!";
		ResponseEntity<String> result = companycontroller.register(company);
		Assert.assertEquals(insert, result.getBody());
	}
	
	@Test
	void performCompanyLogin() {
	    Company company = new Company();
	    company.setEmail("tcs@gmail.com");
	    company.setPassword("Tcs@12345");
	    ResponseEntity<?> response = companycontroller.login(company);
	    Map<String, Long> map = (Map<String, Long>) response.getBody();
	    Assert.assertEquals(map, Map.of("userId", 3L));
	}
	
	@Test
	void performGetAllCompany() {
		Long companyId = 1L;
		List<Company> companyList = companycontroller.getAllCompany();
		assertNotNull(companyList);
	}


	@Test
	void performGetCompanyById() {
		Long companyId = 1L;
		List<Long> companyList = companycontroller.getCompanyIdlists();
		assertNotNull(companyList);
	}
	
}
