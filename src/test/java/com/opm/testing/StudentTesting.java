package com.opm.testing;
 

 

 


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import com.opm.controller.StudentController;
import com.opm.entity.Student;
 
@SpringBootTest
class StudentTesting {
	
	@Autowired
	StudentController studentcontroller;
	
 
	@Test
	void performStudentRegistration() {
		Student student = new Student();
		student.setName("Vairavan");
		student.setGender("Female");
		student.setEmail("vairavan@gmail.com");
		student.setPassword("Vairavan@123");
		student.setPhoneNumber("7401064820");
		student.setAddress("Pudhukottai");
		student.setDepartment("EEE");
		student.setCgpa((float) 8.5);
		student.setStatus("Pending");
		
		String insert = "Student registered successfully!";
		ResponseEntity<String> result = studentcontroller.register(student);
		Assert.assertEquals(insert, result.getBody());
	}
	
	
	@Test
	void performStudentLogin() {
	    Student student = new Student();
	    student.setEmail("varshinee@gmail.com");
	    student.setPassword("Varshinee@1012");
	    ResponseEntity<?> response = studentcontroller.login(student);
	    Map<String, Long> map = (Map<String, Long>) response.getBody();
	    Assert.assertEquals(map, Map.of("userId", 1L));
	}

	@Test
	void performGetStudentById() {
		Long studentId = 1L;
		List<Long> studentList = studentcontroller.getStudentIdlists();
		assertNotNull(studentList);
	}
	
	@Test
	void performGetITTeamIdlist() {
		List<Student> itteamIdList = studentcontroller.getAllStudents();
		assertNotNull(itteamIdList);
	}
	
	@Test
void performUpdate() {
    Student student = new Student();
    student.setStudentId(17l);
    student.setName("Santhiya");
    student.setStatus("Approved");
   
    
    
    ResponseEntity<?> response = studentcontroller.updateStudent(student.getStudentId(), student);
    
   
    assertNotNull(response);
    assertEquals(HttpStatus.OK, response.getStatusCode());
}
	

 
}