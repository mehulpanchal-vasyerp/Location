package com.crud.locationlookup.service;

import java.util.List;

import com.crud.locationlookup.dto.CityDTO;
import com.crud.locationlookup.dto.CountryDTO;
import com.crud.locationlookup.dto.StateDTO;

public interface CountryService {

	List<CountryDTO> getAllCountries();

	CountryDTO getCountryByName(String countryName);

	CountryDTO addCountry(int id, CountryDTO countryDTO);

	void deleteCountry(int id);


	List<StateDTO> getAllStatesByCountry(String countryName);

	List<CityDTO> getAllCitiesByState(String countryName, String stateName);
	

	CountryDTO assignStateToCountry(int countryId, int stateId);
    
    StateDTO assignCityToState(int stateId, int cityId);

}
