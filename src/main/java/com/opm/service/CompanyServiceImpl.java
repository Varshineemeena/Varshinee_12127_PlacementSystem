package com.opm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.opm.dao.CompanyDAO;
import com.opm.entity.Company;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	CompanyDAO dao;
	
	@Override
	public void addCompany(Company company) {
		dao.addCompany(company);
		
	}

	@Override
	public List<Company> getallCompanys() {
		return dao.getallCompanys();
	}

	@Override
	public Optional<Company> getCompanyByEmailId(String email) {
		return dao.getCompanyByEmailId(email);
//		return Optional.empty();
	}

	@Override
	public List<Long> getCompanyIdlist() {
		return dao.getCompanyIdlist();
	}

	@Override
	public Company getCompanyId(Long id) {
		return dao.getCompanyId(id);
	}

	@Override
	public ResponseEntity<?> updateBill(long id, Company updatedCompany) {
		return dao.updateBill(id, updatedCompany);
	}

	@Override
	public void deleteCompany(Long id) {
		dao.deleteCompany(id);
		
	}

}
