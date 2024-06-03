package com.opm.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opm.dao.ApplicationDAOImpl;
import com.opm.dao.JobDAOImpl;
import com.opm.entity.Applications;
import com.opm.entity.Company;
import com.opm.entity.Job;
import com.opm.entity.Student;
import com.opm.service.ApplicationServiceImpl;
import org.hibernate.query.Query;

import jakarta.persistence.EntityManager;
import jakarta.ws.rs.core.Application;


@RestController
@CrossOrigin("http://localhost:3000/")
public class ApplicationController {
	
	
	@Autowired
	ApplicationServiceImpl dao;
	
	@Autowired
	JavaMailSender emailsender;
	
	@Autowired
	EntityManager eman;
	
	@PostMapping("/CreateApplication")
    public String addApplication(@RequestBody Applications applications) {
        String msg="";
        try {
            dao.addApplication(applications);
            msg="Application Details Saved";
        }catch(Exception e) {
            msg="Application Details not saved";
        }
        return msg;
    }
	
	
	@GetMapping("/getAllApplication")
    public List<Applications> getallApplications() {
        try {
            return dao.getallApplications();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
	

 
 @GetMapping("/getApplicationBySid/{id}")
 public List<Applications> getByStudentId(@PathVariable long id){
	 try {
		return dao.getByStudentId(id);
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
 }
 
 @GetMapping("/getApplicationByCid/{id}")
 public List<Applications> getByCompanyId(@PathVariable long id){
	 try {
		return dao.getByCompanyId(id);
	} catch (Exception e) {
		e.printStackTrace();
		return null;
	}
 }


 @PutMapping("/updateapplication/{id}")
 public ResponseEntity<?> updateApplication(@PathVariable("id") Long id,
                                       @RequestBody Applications updatedBill) {
     return dao.updateBill(id, updatedBill);
 }
 
 
 
 @PutMapping("/updateinterview/{id}")
 public ResponseEntity<?> updateInterview(@PathVariable("id") Long id,
                                       @RequestBody Applications updatedInterview) {
     return dao.updateInterview(id, updatedInterview);
 }
 

 
 @GetMapping("/getApplicationById/{id}")
 public Applications getApplication(@PathVariable Long id) {
     try {
         return dao.getApplicationId(id);
     } catch (Exception e) {
         e.printStackTrace();
     }

     return null;
 }
 
 @DeleteMapping("/DeleteApplication/{id}")
 public ResponseEntity<?> deleteApplication(@PathVariable Long id) {
     try {
         dao.deleteApplication(id);
         return ResponseEntity.ok().build();
     } catch (Exception e) {
         // Handle the exception appropriately
         return ResponseEntity.badRequest().body("Error deleting application: " + e.getMessage());
     }
 }
 
 
 




}
