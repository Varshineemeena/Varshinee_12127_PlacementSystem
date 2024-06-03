package com.opm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.opm.entity.Student;

public interface StudentService {
	
	public void addStudent(Student student);

	 List<Student> getallStudents();
	 
//	 List<Student> getStudentById(Long id);
	 
	 Optional<Student> getStudentByEmailId(String email);
	 
	 List<Long> getStudentIdlist();
	 
	 //Approve
	 
	 Student getStudentId(Long id);
	 
//	 Optional<Student> getStudentByIdEmail(Long id);
	 
	 ResponseEntity<?> updateBill(long id,Student updatedStudent);
	 
	 void deleteStudent(Long id);
	 

}
