package com.crud.locationlookup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crud.locationlookup.model.City;

public interface CityRepository extends JpaRepository<City, Integer> {
	
	
	@Query("Select C from City C where C.cityName=:cityName")
	City findByName(String cityName);

}
