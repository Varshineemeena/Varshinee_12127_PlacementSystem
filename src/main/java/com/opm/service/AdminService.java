package com.opm.service;

import java.util.List;
import java.util.Optional;

import com.opm.entity.Admin;

public interface AdminService {
	public void addAdmin(Admin admin);

	 List<Admin> getallAdmins();
	 
	 List<Admin> getAdminById(int id);
	 
	 Optional<Admin> getAdminByEmailId(String email);
	 
	 List<Long> getAdminIdlist();


}
