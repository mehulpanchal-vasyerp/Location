package com.crud.locationlookup.dto;

import com.crud.locationlookup.model.State;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {



	private int id;

	private String cityName;
	
//	private State state;
}
