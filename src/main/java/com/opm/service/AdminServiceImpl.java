package com.opm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opm.dao.AdminDAO;
import com.opm.entity.Admin;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminDAO {

	@Autowired
	AdminDAO dao;
	
	@Override
	public void addAdmin(Admin admin) {
		 dao.addAdmin(admin);
		
	}

	@Override
	public List<Admin> getallAdmins() {
		return dao.getallAdmins();
	}

	@Override
	public List<Admin> getAdminById(int id) {
		return dao.getAdminById(id);
	}

	@Override
	public Optional<Admin> getAdminByEmailId(String email) {
		return dao.getAdminByEmailId(email);
//		return Optional.empty();
	}

	@Override
	public List<Long> getAdminIdlist() {
		return dao.getAdminIdlist();
	}

}
