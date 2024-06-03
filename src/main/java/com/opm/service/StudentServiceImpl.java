package com.opm.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.opm.dao.StudentDAO;
import com.opm.entity.Student;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentDAO dao;
	

	@Override
	public List<Student> getallStudents() {
		return dao.getallStudents();
		
	}

	@Override
	public Optional<Student> getStudentByEmailId(String email) {
		return dao.getStudentByEmailId(email);
//		return Optional.empty();
	}

	@Override
	public List<Long> getStudentIdlist() {
		return dao.getStudentIdlist();
		
	}

	@Override
	public Student getStudentId(Long id) {
		return dao.getStudentId(id);
		 
	}

	@Override
	public ResponseEntity<?> updateBill(long id, Student updatedStudent) {
		return dao.updateBill(id, updatedStudent);
	}

	@Override
	public void addStudent(Student student) {
		dao.addStudent(student);
		
	}

	@Override
	public void deleteStudent(Long id) {
		dao.deleteStudent(id);
		
	}


	
	
	 }

	

	

