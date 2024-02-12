package com.crud.locationlookup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.crud.locationlookup.dto.CountryDTO;
import com.crud.locationlookup.model.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{

	
	@Query("Select C from Country C where C.countryName=:countryName")
//	@Query(value = "SELECT * FROM Country WHERE country_name = :countryName",nativeQuery = true)
	Country findByName(@Param("countryName")String countryName);
	
	@Query("Select C from Country C Order By C.id Asc")
	List<Country> findAll();

}
