package com.crud.locationlookup.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.locationlookup.dto.CityDTO;
import com.crud.locationlookup.dto.CountryDTO;
import com.crud.locationlookup.dto.StateDTO;
import com.crud.locationlookup.exception.CityAlreadyAssignedException;
import com.crud.locationlookup.exception.CityNotFoundException;
import com.crud.locationlookup.exception.CountryNotFoundException;
import com.crud.locationlookup.exception.StateAlreadyAssignedException;
import com.crud.locationlookup.exception.StateNotFoundException;
import com.crud.locationlookup.model.City;
import com.crud.locationlookup.model.Country;
import com.crud.locationlookup.model.State;
import com.crud.locationlookup.repository.CityRepository;
import com.crud.locationlookup.repository.CountryRepository;
import com.crud.locationlookup.repository.StateRepository;

@Service
public class CountryServiceImpl implements CountryService {
	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private StateRepository stateRepository;

	
	@Autowired
	private CityRepository cityRepository;


	@Override
	public List<CountryDTO> getAllCountries() {
		List<CountryDTO> list = new ArrayList<>();
		List<Country> countryList = countryRepository.findAll();
		for (Country country : countryList) {
			CountryDTO countryDto = new CountryDTO();
			countryDto.setId(country.getId());
			countryDto.setCountryName(country.getCountryName());
			list.add(countryDto);

		}
		return list;
	}

	@Override
	public CountryDTO getCountryByName(String countryName) {

		Country country = countryRepository.findByName(countryName);
		if (country != null) {

			CountryDTO countryDTO = new CountryDTO();
			countryDTO.setId(country.getId());
			countryDTO.setCountryName(country.getCountryName());
			return countryDTO;
		} else {
			throw new CountryNotFoundException("Country not found with name: " + countryName);
		}
	}

	@Override
	public CountryDTO addCountry(int id, CountryDTO countryDTO) {
		if (id == 0) {
			// Create a new country
			Country newCountry = new Country();
			newCountry.setId(countryDTO.getId());
			newCountry.setCountryName(countryDTO.getCountryName());
			// Set other fields as needed
			countryRepository.save(newCountry);

			// Convert and return the new country as DTO
			return new CountryDTO(newCountry.getId(), newCountry.getCountryName()
			// Add other fields as needed
			);

		} else {
			Country optionalCountry = countryRepository.findById(id)
					.orElseThrow(() -> new CountryNotFoundException("Country id is not found"));
			Country countryToUpdate = optionalCountry;
			// Update the country entity with the data from DTO
			if (countryDTO.getCountryName() != null) {
				countryToUpdate.setCountryName(countryDTO.getCountryName());
			}

			// Save the updated or new country
			countryRepository.save(countryToUpdate);
			return new CountryDTO(countryToUpdate.getId(), countryToUpdate.getCountryName());

		}
	}

	@Override
	public void deleteCountry(int id) {
		Country orElseThrow = countryRepository.findById(id)
				.orElseThrow(() -> new CountryNotFoundException(" Id is Not Found For country" + id));
		countryRepository.deleteById(id);
	}

	@Override
	public List<StateDTO> getAllStatesByCountry(String countryName) {
		Country country = countryRepository.findByName(countryName);
		if (country != null) {
			// Assuming you have a 'states' collection in the Country entity
			List<StateDTO> stateDTOs = new ArrayList<>();
			for (State state : country.getStates()) {
				StateDTO stateDTO = new StateDTO();
				stateDTO.setId(state.getId());
				stateDTO.setStateName(state.getStateName());
				// Set other fields as needed
				stateDTOs.add(stateDTO);
			}
			return stateDTOs;
		} else {
			throw new CountryNotFoundException("Country not found with name: " + countryName);
		}
	}

	@Override
	public List<CityDTO> getAllCitiesByState(String countryName, String stateName) {
		Country country = countryRepository.findByName(countryName);
		if (country != null) {
			State state = findStateInCountry(country, stateName);
			if (state != null) {
				List<CityDTO> cityDTOs = new ArrayList<>();
				for (City city : state.getCities()) {
					CityDTO cityDTO = new CityDTO();
					cityDTO.setId(city.getId());
					cityDTO.setCityName(city.getCityName());
					cityDTOs.add(cityDTO);
				}
				return cityDTOs;
			} else {
				throw new CountryNotFoundException("State not found with name: " + stateName);
			}
		} else {
			throw new CountryNotFoundException("Country not found with name: " + countryName);
		}
	}

	private State findStateInCountry(Country country, String stateName) {
		for (State state : country.getStates()) {
			if (state.getStateName().equals(stateName)) {
				return state;
			}
		}
		return null;
	}

	@Override
	public CountryDTO assignStateToCountry(int countryId, int stateId) {
		Country country = countryRepository.findById(countryId)
				.orElseThrow(() -> new CountryNotFoundException("Country not found with id: " + countryId));

		State state = stateRepository.findById(stateId)
				.orElseThrow(() -> new StateNotFoundException("State not found with id: " + stateId));

		if (country.getStates().contains(state)) {
			throw new StateAlreadyAssignedException("This state is already assigned to this country.");
		}

		state.setCountry(country);
		State save = stateRepository.save(state);

		return new CountryDTO(save);
	}

	@Override
	public StateDTO assignCityToState(int stateId, int cityId) {
		State state = stateRepository.findById(stateId)
				.orElseThrow(() -> new StateNotFoundException("Country not found with id: " + stateId));

		City city = cityRepository.findById(cityId)
				.orElseThrow(() -> new CityNotFoundException("State not found with id: " + stateId));

		if (state.getCities().contains(city)) {
			throw new CityAlreadyAssignedException("This state is already assigned to this country.");
		}

		city.setState(state);
		City save = cityRepository.save(city);

		return new StateDTO(save);

	}

}
