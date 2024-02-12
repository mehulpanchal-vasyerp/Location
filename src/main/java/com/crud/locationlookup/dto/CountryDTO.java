package com.crud.locationlookup.dto;

import com.crud.locationlookup.model.Country;
import com.crud.locationlookup.model.State;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {

	public CountryDTO(String countryName) {
		this.countryName = countryName;
	}

	public CountryDTO(Country save) {
		// TODO Auto-generated constructor stub
	}

	public CountryDTO(State save) {
		// TODO Auto-generated constructor stub
	}

	private int id;

	private String countryName;

}
