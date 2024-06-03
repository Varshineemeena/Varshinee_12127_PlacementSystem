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
import org.springframework.web.bind.annotation.RestController;

import com.opm.dao.StudentDAOImpl;
import com.opm.entity.Admin;
import com.opm.entity.Company;
import com.opm.entity.Student;
import com.opm.service.StudentServiceImpl;

@RestController
@CrossOrigin("http://localhost:3000/")
public class StudentController {
	

	
	@Autowired
	StudentServiceImpl dao;
	
	@Autowired
	JavaMailSender emailsender;
	

	
	@GetMapping("/getAllStudents")
    public List<Student> getAllStudents() {
        try {
            return dao.getallStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
	

	

	
 @PostMapping("/registerStudent")
 public ResponseEntity<String> register(@RequestBody Student student) {
     try {
         // Validate input data (you can add more validation logic)
         if (student.getEmail() == null || student.getPassword() == null || student.getPhoneNumber() == null || student.getGender() == null || student.getAddress() == null || student.getDepartment() == null || student.getCgpa() == null || student.getName() == null) {
         }

         // Save admin to the database
         dao.addStudent(student);
         
         SimpleMailMessage message=new SimpleMailMessage();
         message.setFrom("msvarshinee@gmail.com");
         message.setTo(student.getEmail());
         message.setSubject("Login Alert...!!!");
         message.setText("You're successfully Registered Please wait for the approval of the admin Once approved you will get your login credentials");
         
         emailsender.send(message);

         return ResponseEntity.ok("Student registered successfully!");
     } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error occurred during registration: " + e.getMessage());
     }
 }
 
 @PostMapping("/loginStudent")
 public ResponseEntity<?> login(@RequestBody Student student) {
     try {
         // Find the Student by email
         Optional<Student> studentOptional = dao.getStudentByEmailId(student.getEmail());
        
         
         
         if (!studentOptional.isPresent()) {
             return ResponseEntity.badRequest().body("Invalid email");
         }

         Student studentFound = studentOptional.get();
         Map<String, Long> map=new HashMap<>();
         map.put("userId", studentFound.getStudentId());

         // Check if password matches
         if (!student.getPassword().equals(studentFound.getPassword())) {
             return ResponseEntity.badRequest().body("Invalid password");
         }
         
         return ResponseEntity.ok(map);
     } catch (Exception e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error occurred during login: " + e.getMessage());
     }
     
     
 }
 
 @GetMapping("/GetStudentIds")
 public List<Long> getStudentIdlists() {
     try {
         return dao.getStudentIdlist();
     } catch (Exception e) {
         e.printStackTrace();
     }
     return null;
 }
 
 //Approve
 
 @PutMapping("/update/{id}")
 public ResponseEntity<?> updateStudent(@PathVariable("id") Long id,
                                       @RequestBody Student updatedBill) {
     return dao.updateBill(id, updatedBill);
     
     
     
     
 }
 
 
 
 @PostMapping("/sendEmail/{id}")
 public ResponseEntity<?> sendStudentEmail(@PathVariable("id") Long id) {
     Optional<Student> studentOptional = Optional.ofNullable(dao.getStudentId(id));
     if (!studentOptional.isPresent()) {
         return ResponseEntity.badRequest().body("Student not found");
     }
     Student student = studentOptional.get();

     SimpleMailMessage message = new SimpleMailMessage();
     message.setFrom("msvarshinee@gmail.com");
     message.setTo(student.getEmail());
     message.setSubject("Registration Successful...!!!");
     message.setText("Your Registration has been approved " + student.getName()+" "+"You can Login through the following credentials"+" "+ "Username - "+" "+student.getEmail()+" "+ "Password - "+" "+student.getPassword());

     emailsender.send(message);

     return ResponseEntity.ok().body("Email sent to " + student.getEmail());
 }
 

 @GetMapping("/getStudentById/{id}")
 public Student getStudent(@PathVariable Long id) {
     try {
         return dao.getStudentId(id);
     } catch (Exception e) {
         e.printStackTrace();
     }

     return null;
 }
 
 @DeleteMapping("/deletestudent/{id}")
 public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
     try {
         dao.deleteStudent(id);
         return ResponseEntity.ok().build();
     } catch (Exception e) {
         // Handle the exception appropriately
         return ResponseEntity.badRequest().body("Error deleting application: " + e.getMessage());
     }
 }
 
	
	
	
}
