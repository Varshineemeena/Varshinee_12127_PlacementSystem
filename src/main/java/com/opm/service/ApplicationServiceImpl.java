package com.opm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.opm.dao.ApplicationsDAO;
import com.opm.entity.Applications;
import com.opm.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {
	
	@Autowired
	ApplicationsDAO dao;
	
	@Autowired
	EntityManager eman;
	
	@Autowired
	JavaMailSender emailsender;

	@Override
	public void addApplication(Applications applications) {
		dao.addApplication(applications);
		
	}

	@Override
	public List<Applications> getallApplications() {
		return dao.getallApplications();
	}

	@Override
	public List<Applications> getByStudentId(Long id) {
		return dao.getByStudentId(id);
	}

	@Override
	public List<Applications> getByCompanyId(Long id) {
		return dao.getByCompanyId(id);
	}

	@Override
	public Applications getApplicationId(Long id) {
		return dao.getApplicationId(id);
	}

	@Override
	public ResponseEntity<?> updateBill(long id, Applications updatedApplication) {
		return dao.updateBill(id, updatedApplication);
	}

	@Override
	public ResponseEntity<?> updateInterview(long id, Applications updatedInterview) {
		
		Applications application= eman.find(Applications.class, id);
		
		Student student=eman.find(Student.class, application.getStudent().getStudentId());
		String email=student.getEmail();
		
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("msvarshinee@gmail.com");
		message.setTo(email);
		message.setSubject("Interview Alert....!!!");
		message.setText("Your Application status has been approved"+"/n Interview date:10.06.2024"+"/nInterview time:10:00 AM");
		
		emailsender.send(message);
		
		
		return dao.updateInterview(id, updatedInterview);
	}

	@Override
    public void deleteApplication(Long id) {
        dao.deleteApplication(id);
    }

}
