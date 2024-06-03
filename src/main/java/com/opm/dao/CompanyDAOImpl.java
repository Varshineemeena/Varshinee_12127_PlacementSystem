package com.opm.dao;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import com.opm.entity.Company;
import com.opm.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CompanyDAOImpl implements CompanyDAO {
	
	private EntityManager eman;

	public CompanyDAOImpl() {
		super();
		
	}
	
	@Autowired
	public CompanyDAOImpl(EntityManager entity) {
		super();
		this.eman=entity;
	}
	
	@Override
    public void addCompany(Company company) {
        try {
            eman.persist(company);
           
        }catch(Exception e) {
            e.printStackTrace();
        }
	}
	
	@Override
    public List<Company> getallCompanys() {
        List<Company> company = new ArrayList<>();
        try {
            company = eman.createQuery("from Company E").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return company;
    }
	

    
    public void updateCompanyInfo(Company company) {
        try {
            eman.createQuery
            ("UPDATE Company E SET E.name= :name, E.address= :address, E.email = :email, E.password = :password, E.url = :url WHERE E.companyId= :companyId")
            .setParameter("companyId", company.getCompanyId())
            .setParameter("name", company.getName()).setParameter("address", company.getAddress())
            .setParameter("email", company.getEmail()).setParameter("password", company.getPassword()).setParameter("url", company.getUrl()).setParameter("status", company.getStatus())
            .executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Optional<Company> getCompanyByEmailId(String email) {
        return eman.createQuery("SELECT c FROM Company c WHERE c.email = :email", Company.class)
              .setParameter("email", email)
              .getResultStream()
              .findFirst();
    }
    
    @Override
	public List<Long> getCompanyIdlist() {
		Query q = eman.createQuery("SELECT c.id FROM Company c");
		List<Long> list = q.getResultList();
		return list;
	}
    


	
	@Override
	public ResponseEntity<?> updateBill(long companyId, Company updatedCompany) {
		Company existingCompany = eman.find(Company.class, companyId);
				
        if (existingCompany == null) {
            return ResponseEntity.badRequest().body("Company not found");
        }
        
        // Update
        existingCompany.setStatus(updatedCompany.getStatus());
//        existingStudent.setAdmin(updatedStudent.getAdmin());

        // Save the update
        eman.persist(existingCompany);
        return ResponseEntity.ok("Details updated successfully");
	}

	@Override
	public Company getCompanyId(Long id) {
		return eman.find(Company.class, id);
		
	}
	
	@Override
	public void deleteCompany(Long id) {
	     Company companyById = getCompanyById(id);
	     eman.remove(companyById);
	 }
	public Company getCompanyById(Long id) {
		return eman.find(Company.class, id);
	}

	
	
	
  }


	
	
	

	
	


