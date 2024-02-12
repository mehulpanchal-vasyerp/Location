package com.crud.locationlookup.repository;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crud.locationlookup.dto.StateDTO;
import com.crud.locationlookup.model.State;

public interface StateRepository extends  JpaRepository<State, Integer>{

	
	@Query("Select S from State S where S.stateName=:stateName")
	State findByName(String stateName);
}
