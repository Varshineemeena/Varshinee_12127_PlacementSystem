package com.opm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.opm.dao.CompanyDAOImpl;
import com.opm.entity.Company;
import com.opm.entity.Student;
import com.opm.service.CompanyServiceImpl;


@RestController
@CrossOrigin("http://localhost:3000/")
public class CompanyController {
	

	
	@Autowired
	CompanyServiceImpl dao;
	
	@Autowired
	JavaMailSender emailsender;
	
	
	@PostMapping("/CreateCompany")
    public String addCompany(@RequestBody Company company) {
        String msg="";
        try {
            dao.addCompany(company);
            msg="Company Details Saved";
        }catch(Exception e) {
            msg="Company Details not saved";
        }
        return msg;
    }
	
	 @GetMapping("/getAllCompany")
	    public List<Company> getAllCompany() {
	        try {
	            return dao.getallCompanys();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return null;
	    }
	
	 

	 

	 
	 @PostMapping("/registerCompany")
	 public ResponseEntity<String> register(@RequestBody Company company) {
	     try {
	         // Validate input data (you can add more validation logic)
	         if (company.getEmail() == null || company.getPassword() == null || company.getAddress() == null || company.getUrl() == null || company.getName() == null) {
	         }

	         // Save admin to the database
	         dao.addCompany(company);
	         SimpleMailMessage message=new SimpleMailMessage();
	         message.setFrom("msvarshinee@gmail.com");
	         message.setTo(company.getEmail());
	         message.setSubject("Login Alert...!!!");
	         message.setText("HI"+company.getName()+" successfully Registered Please wait for the approval of the admin Once approved you will get your login credentials");
	         
	         emailsender.send(message);


	         return ResponseEntity.ok("Company registered successfully!");
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error occurred during registration: " + e.getMessage());
	     }
	 }

	 @PostMapping("/loginCompany")
	 public ResponseEntity<?> login(@RequestBody Company company) {
	     try {
	         // Find the Student by email
	         Optional<Company> companyOptional = dao.getCompanyByEmailId(company.getEmail());
	         if (!companyOptional.isPresent()) {
	             return ResponseEntity.badRequest().body("Invalid email");
	         }

	         Company companyFound = companyOptional.get();
	         Map<String, Long> map=new HashMap<>();
	         map.put("userId", companyFound.getCompanyId());

	         // Check if password matches
	         if (!company.getPassword().equals(companyFound.getPassword())) {
	             return ResponseEntity.badRequest().body("Invalid password");
	         }

	         return ResponseEntity.ok(map);
	     } catch (Exception e) {
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Error occurred during login: " + e.getMessage());
	     }
	 }
	 
	 @PostMapping("/sendEmailCompany/{id}")
	 public ResponseEntity<?> sendCompanyEmail(@PathVariable("id") Long id) {
	     Optional<Company> companyOptional = Optional.of(dao.getCompanyId(id));
	     if (!companyOptional.isPresent()) {
	         return ResponseEntity.badRequest().body("Student not found");
	     }
	     Company company = companyOptional.get();

	     SimpleMailMessage message = new SimpleMailMessage();
	     message.setFrom("msvarshinee@gmail.com");
	     message.setTo(company.getEmail());
	     message.setSubject("Registration Successful...!!!");
	     message.setText("Your Registration has been approved " + company.getName()+" "+"You can Login through the following credentials"+" "+ "Username - "+" "+company.getEmail()+" "+ "Password - "+" "+company.getPassword());

	     emailsender.send(message);

	     return ResponseEntity.ok().body("Email sent to " + company.getEmail());
	 }
	 
	 @GetMapping("/GetCompanyIds")
	    public List<Long> getCompanyIdlists() {
	        try {
	            return dao.getCompanyIdlist();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }
	 @PutMapping("/updatecompany/{id}")
	 public ResponseEntity<?> updateCompany(@PathVariable("id") Long id,
	                                       @RequestBody Company updatedBill) {
	     return dao.updateBill(id, updatedBill);
	 }
	 

	 
	 @GetMapping("/getCompanyById/{id}")
	 public Company getCompany(@PathVariable Long id) {
	     try {
	         return dao.getCompanyId(id);
	     } catch (Exception e) {
	         e.printStackTrace();
	     }

	     return null;
	 }
	 
	 @DeleteMapping("/deletecompany/{id}")
	 public ResponseEntity<?> deleteCompany(@PathVariable Long id) {
	     try {
	         dao.deleteCompany(id);
	         return ResponseEntity.ok().build();
	     } catch (Exception e) {
	         // Handle the exception appropriately
	         return ResponseEntity.badRequest().body("Error deleting application: " + e.getMessage());
	     }
	 }


}
