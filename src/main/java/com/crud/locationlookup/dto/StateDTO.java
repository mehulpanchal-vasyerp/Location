package com.crud.locationlookup.dto;


import com.crud.locationlookup.model.City;
import com.crud.locationlookup.model.Country;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StateDTO {

	


	public StateDTO(City save) {
	}

	private int id;

	private String stateName;

//	private Country country;

}
