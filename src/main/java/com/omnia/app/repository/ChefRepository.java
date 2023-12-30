package com.omnia.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.omnia.app.model.Chef;
import com.omnia.app.response.Cheflist;

@Repository
public interface ChefRepository  extends JpaRepository<Chef,Long >{
	
	
	
	@Query(value = "select chef_id, phone email,chef_name,description from chef c",nativeQuery = true)
	List<Chef> getAllChefs();
	
	
	
	

}
