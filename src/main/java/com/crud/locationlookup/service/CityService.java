package com.crud.locationlookup.service;

import java.util.List;

import com.crud.locationlookup.dto.CityDTO;
import com.crud.locationlookup.dto.StateDTO;

public interface CityService {

	List<CityDTO> getAllCity();

	CityDTO getCityByName(String cityName);

	CityDTO addOrUpdateCity(int id, CityDTO cityDTO);

	void deleteCity(int id);
}
