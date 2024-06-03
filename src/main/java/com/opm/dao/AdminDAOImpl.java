package com.opm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.opm.entity.Admin;
import com.opm.entity.Company;
import com.opm.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class AdminDAOImpl implements AdminDAO {
	
	private EntityManager eman;

	public AdminDAOImpl() {
		super();
		
	}
	
	@Autowired
	public AdminDAOImpl(EntityManager entity) {
		super();
		this.eman=entity;
	}
	
	@Override
    public void addAdmin(Admin admin) {
        try {
            eman.persist(admin);
           
        }catch(Exception e) {
            e.printStackTrace();
        }
	}
	
	@Override
    public List<Admin> getallAdmins() {
        List<Admin> admin = new ArrayList<>();
        try {
        	admin = eman.createQuery("from Admin E").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return admin;
    }
	
	@SuppressWarnings("unchecked")
	@Override
	    public List<Admin> getAdminById(int Id) {
	        List<Admin> admin = new ArrayList<>();
	        try {
	        	admin = eman.createQuery("from Admin E where E.id = :id").setParameter("id", Id).getResultList();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return admin;
	    }
	
	@Override
    public Optional<Admin> getAdminByEmailId(String email) {
        return eman.createQuery("SELECT a FROM Admin a WHERE a.email = :email", Admin.class)
              .setParameter("email", email)
              .getResultStream()
              .findFirst();
    }
	
	@Override
	public List<Long> getAdminIdlist() {
		Query q = eman.createQuery("SELECT s.id FROM Admin s");
		List<Long> list = q.getResultList();
		return list;
	}
	
	

}
