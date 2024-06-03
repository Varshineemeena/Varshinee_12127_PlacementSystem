package com.opm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.opm.dao.AdminDAOImpl;
import com.opm.dao.CompanyDAOImpl;
import com.opm.dao.StudentDAOImpl;
import com.opm.entity.Admin;
import com.opm.entity.Company;
import com.opm.entity.Student;
import com.opm.service.AdminServiceImpl;

@RestController
@CrossOrigin("http://localhost:3000/")
public class AdminController {
	

	@Autowired
	AdminServiceImpl dao;
	
	
	@PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Admin admin) {
        try {
            // Validate input data (you can add more validation logic)
            if (admin.getEmail() == null || admin.getPassword() == null || admin.getName() == null) {
                return ResponseEntity.badRequest().body("All fields are required.");
            }
 
            // Save admin to the database
            dao.addAdmin(admin);
 
            return ResponseEntity.ok("Admin registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body("Error occurred during registration: " + e.getMessage());
        }
    }
 
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Admin admin) {
        try {
            // Find the admin by email
            Optional<Admin> adminOptional = dao.getAdminByEmailId(admin.getEmail());
            if (!adminOptional.isPresent()) {
                return ResponseEntity.badRequest().body("Invalid email");
            }
 
            Admin adminFound = adminOptional.get();
 
            // Check if password matches
            if (!admin.getPassword().equals(adminFound.getPassword())) {
                return ResponseEntity.badRequest().body("Invalid password");
            }
 
            return ResponseEntity.ok("Login successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                   .body("Error occurred during login: " + e.getMessage());
        }
    }
	
	@GetMapping("/getAllAdmin")
    public List<Admin> getAllAdmin() {
        try {
            return dao.getallAdmins();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
	
	 @GetMapping("/GetAdminIds")
	 public List<Long> getAdminIdlists() {
	     try {
	         return dao.getAdminIdlist();
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
	     return null;
	 }

	
	


}
