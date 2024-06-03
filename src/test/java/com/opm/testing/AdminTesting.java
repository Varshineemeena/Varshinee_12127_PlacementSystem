package com.opm.testing;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.testng.Assert;

import com.opm.controller.AdminController;
import com.opm.entity.Admin;
import com.opm.entity.Company;

@SpringBootTest
class AdminTesting {
	
	@Autowired
	AdminController admincontroller;
	
//	@Test
//	void performAdminRegistration() {
//		Admin admin = new Admin();
//		admin.setName("Sowmiya");
//		admin.setEmail("sowmiya@gmail.com");
//		admin.setPassword("Tcs@12345");
//		
//		String insert = "Admin registered successfully!";
//		ResponseEntity<String> result = admincontroller.register(admin);
//		Assert.assertEquals(insert, result.getBody());
//	}
//	
	
//	@Test
//	void performAdminLogin() {
//	    Admin admin = new Admin();
//	    admin.setEmail("sowmiya@gmail.com");
//	    admin.setPassword("Sowmiya@12345");
//	    ResponseEntity<?> response = admincontroller.login(admin);
//	    Map<String, Long> map = (Map<String, Long>) response.getBody();
//	    Assert.assertEquals(map, Map.of("userId", 2L));
//	}
	


	@Test
	void performGetAdminById() {
		Long adminId = 1L;
		List<Long> adminList = admincontroller.getAdminIdlists();
		assertNotNull(adminList);
	}
	

	

}
