package com.opm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.opm.entity.Student;
import com.opm.service.MailService;
 

 
@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class MailController {

 
	@Autowired
	MailService serv;
 
	@PostMapping("/sendmail")
	public String sendOTPEmail(@RequestParam("email")String email){
		String toMail = email;
		String subject = "Placement and Management - Registration Confirmation";
		String body = "Good Afternoon I'm Meena Your Placement Admin, Your Registration is Successfull Now You can Login Using the Your Credentials";
		try {
			serv.sendMail(toMail, subject, body);
			return "Mail Sent Successfully";
		}catch(Exception e) {
			return "Mail Send Failure";
		}
 
	}
}