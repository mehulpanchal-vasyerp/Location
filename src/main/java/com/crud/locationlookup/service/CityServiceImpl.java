package com.crud.locationlookup.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.locationlookup.dto.CityDTO;
import com.crud.locationlookup.exception.CountryNotFoundException;
import com.crud.locationlookup.model.State;
import com.crud.locationlookup.model.City;
import com.crud.locationlookup.repository.CityRepository;
import com.crud.locationlookup.repository.CountryRepository;
import com.crud.locationlookup.repository.StateRepository;


@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private StateRepository stateRepository;


	@Override
	public List<CityDTO> getAllCity() {
		List<CityDTO> list = new ArrayList<>();
		List<City> cities = cityRepository.findAll();
		for (City city : cities) {
			CityDTO dto = new CityDTO();
			dto.setId(city.getId());
			dto.setCityName(city.getCityName());

//			Country country = states.get();
//			if (country != null) {
//				CountryDTO countryDTO = new CountryDTO();
//				countryDTO.setId(states.getId());
//				countryDTO.setCountryName(states.getCountryName());
//				dto.setCountry(country);
//			}
			list.add(dto);
		}
		return list;
	}

	@Override
	public CityDTO getCityByName(String cityName) {
		
		City city = cityRepository.findByName(cityName);
		if (city != null) {
			CityDTO countryDTO = new CityDTO();
			countryDTO.setId(city.getId());
			countryDTO.setCityName(city.getCityName());
			return countryDTO;
		} else {
			throw new CountryNotFoundException("State not found with name: " + cityName);
		}
	}

	@Override
	public CityDTO addOrUpdateCity(int id, CityDTO cityDTO) {
		if (id == 0) {
			// Create a new country
			City newCity = new City ();

			newCity.setId(cityDTO.getId());
			newCity.setCityName(cityDTO.getCityName());
//			Country country = null;
//			if (stateDTO.getCountry().getCountryName() != null) {
//				country = countryRepository.findById(stateDTO.getCountry().getId())
//						.orElseThrow(() -> new CountryNotFoundException("Country Not found: " + id));
//				country.setCountryName(stateDTO.getCountry().getCountryName());
//				newstate.setCountry(country);
//			}
//			newCity.setState(cityDTO.getState());
			cityRepository.save(newCity);

			return new CityDTO(newCity.getId(), newCity.getCityName()
//					,newCity.getState()
			);

		} else {
			City optionalCity = cityRepository.findById(id)
					.orElseThrow(() -> new CountryNotFoundException("Country id is not found"));
			City cityToUpdate = optionalCity;
			// Update the country entity with the data from DTO
			if (cityDTO.getCityName() != null) {
				cityToUpdate.setCityName(cityDTO.getCityName());
			}

			// Save the updated or new country
			cityRepository.save(cityToUpdate);
			return new CityDTO(cityToUpdate.getId(), cityToUpdate.getCityName()
					
//					,cityToUpdate.getState()
					);
		}

	}

	@Override
	public void deleteCity(int id) {
		cityRepository.findById(id).orElseThrow(()-> new CountryNotFoundException("City Id not Found: "+id));
		cityRepository.deleteById(id);
	}

}
