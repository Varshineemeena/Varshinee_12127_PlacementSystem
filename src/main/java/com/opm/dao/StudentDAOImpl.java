package com.opm.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.opm.entity.Admin;
import com.opm.entity.Applications;
import com.opm.entity.Company;
import com.opm.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class StudentDAOImpl implements StudentDAO {
	
	private EntityManager eman;

	public StudentDAOImpl() {
		super();
		
	}
	
	@Autowired
	public StudentDAOImpl(EntityManager entity) {
		super();
		this.eman=entity;
	}
	
	@Override
    public void addStudent(Student student) {
        try {
            eman.persist(student);
           
        }catch(Exception e) {
            e.printStackTrace();
        }
	}
	
	@Override
    public List<Student> getallStudents() {
        List<Student> student = new ArrayList<>();
        try {
        	student = eman.createQuery("from Student E").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return student;
    }
	
	public void updateStudentInfo(Student student) {
        try {
            eman.createQuery("UPDATE Student E SET E.name= :name, E.gender= :gender, E.email= :email,  E.password= :password, E.phoneNumber = :phoneNumber, E.address= :address, E.department= :department, E.cgpa= :cgpa, E.status= :status WHERE E.studentId= :studentId")
            .setParameter("studentId", student.getStudentId()).setParameter("name", student.getName()).setParameter("gender", student.getGender())
            .setParameter("email", student.getEmail()).setParameter("password", student.getPassword()).setParameter("phoneNumber", student.getPhoneNumber()).setParameter("address", student.getAddress()).setParameter("department", student.getDepartment()).setParameter("cgpa", student.getCgpa()).setParameter("status", student.getStatus())
            .executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	

	
	

    

	@Override
    public Optional<Student> getStudentByEmailId(String email) {
        return eman.createQuery("SELECT s FROM Student s WHERE s.email = :email", Student.class)
              .setParameter("email", email)
              .getResultStream()
              .findFirst();
    }
	
	
	
	// Approve 
	
	@Override
	public List<Long> getStudentIdlist() {
		Query q = eman.createQuery("SELECT s.id FROM Student s");
		List<Long> list = q.getResultList();
		return list;
	}
	
	@Override
	public ResponseEntity<?> updateBill(long studentId, Student updatedStudent) {
		Student existingStudent = eman.find(Student.class, studentId);
				
        if (existingStudent == null) {
            return ResponseEntity.badRequest().body("Student not found");
        }
        
        // Update
        existingStudent.setStatus(updatedStudent.getStatus());
//        existingStudent.setAdmin(updatedStudent.getAdmin());

        // Save the update
        eman.persist(existingStudent);
        return ResponseEntity.ok("Details updated successfully");
	}

	@Override
	public Student getStudentId(Long id) {
		return eman.find(Student.class, id);
		
	}
	

	
	@Override
    public void deleteStudent(Long id) {
        Student applicationById = getStudentById(id);
        eman.remove(applicationById);
    }
	
	public Student getStudentById(Long id) {
		return eman.find(Student.class, id);
	}

	
}
	
	
	
	


